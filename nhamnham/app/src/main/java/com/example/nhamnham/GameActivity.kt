package com.example.nhamnham

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.GridLayout
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class GameActivity : AppCompatActivity() {

    private lateinit var gridBoard: GridLayout
    private lateinit var txtCurrentPlayer: TextView


    // Representação do tabuleiro (3x3)
    private val board = Array(3) { Array(3) { Pair("", PieceSize.PEQUENO) } }

    private val piecesAvailable = mutableMapOf(
        "Jogador 1" to mutableListOf(PieceSize.PEQUENO, PieceSize.PEQUENO, PieceSize.PEQUENO,
            PieceSize.MEDIO, PieceSize.MEDIO, PieceSize.MEDIO,
            PieceSize.GRANDE, PieceSize.GRANDE, PieceSize.GRANDE),
        "Jogador 2" to mutableListOf(PieceSize.PEQUENO, PieceSize.PEQUENO, PieceSize.PEQUENO,
            PieceSize.MEDIO, PieceSize.MEDIO, PieceSize.MEDIO,
            PieceSize.GRANDE, PieceSize.GRANDE, PieceSize.GRANDE)
    )


    // Indicação do jogador atual
    private var currentPlayer = "Jogador 1"

    private lateinit var spinnerPieceSize: Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        gridBoard = findViewById(R.id.gridBoard)
        txtCurrentPlayer = findViewById(R.id.txtCurrentPlayer)
        spinnerPieceSize = findViewById(R.id.spinnerPieceSize)

        val btnRestart = findViewById<Button>(R.id.btnRestart)

        setupBoard()
        setupSpinner()

        btnRestart.setOnClickListener {
            resetGame()
        }

        // voltar à tela inicial
        val btnBack = findViewById<Button>(R.id.btnVoltar)
        btnBack.setOnClickListener {
            finish()
        }
    }

    private fun setupSpinner() {
        val pieceSizes = listOf("PEQUENO", "MEDIO", "GRANDE")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, pieceSizes)
        spinnerPieceSize.adapter = adapter
    }

    private fun setupBoard() {
        for (i in 0 until 3) {
            for (j in 0 until 3) {
                val cell = gridBoard.getChildAt(i * 3 + j) as ImageView
                cell.setImageResource(0) // Limpa a imagem
                cell.setOnClickListener {
                    onCellClicked(cell, i, j)
                }
            }
        }
    }

    private fun onCellClicked(cell: ImageView, row: Int, col: Int) {
        val selectedSize = when (spinnerPieceSize.selectedItem.toString()) {
            "PEQUENO" -> PieceSize.PEQUENO
            "MEDIO" -> PieceSize.MEDIO
            "GRANDE" -> PieceSize.GRANDE
            else -> return
        }

        val currentPiece = board[row][col]
        if (currentPiece.first.isEmpty() || selectedSize.value > currentPiece.second.value) {
            // Atualizar tabuleiro
            board[row][col] = Pair(currentPlayer, selectedSize)

            // Define a imagem correspondente à peça
            val pieceImageRes = getPieceImageResource(currentPlayer, selectedSize)
            cell.setImageResource(pieceImageRes)

            // Remover peça usada do jogador
            piecesAvailable[currentPlayer]?.remove(selectedSize)

            // Verificar vencedor ou alternar jogador
            if (checkWinner()) {
                txtCurrentPlayer.text = "$currentPlayer venceu!"
                disableBoard()
            } else {
                switchPlayer()
                updateSpinner()
            }
        }
    }

    private fun getPieceImageResource(player: String, size: PieceSize): Int {
        return when (player) {
            "Jogador 1" -> when (size) {
                PieceSize.PEQUENO -> R.drawable.piece_small_player1
                PieceSize.MEDIO -> R.drawable.piece_medium_player1
                PieceSize.GRANDE -> R.drawable.piece_large_player1
            }
            "Jogador 2" -> when (size) {
                PieceSize.PEQUENO -> R.drawable.piece_small_player2
                PieceSize.MEDIO -> R.drawable.piece_medium_player2
                PieceSize.GRANDE -> R.drawable.piece_large_player2
            }
            else -> 0
        }
    }

    private fun updateSpinner() {
        val availablePieces = piecesAvailable[currentPlayer]?.map { it.name }?.distinct()
        spinnerPieceSize.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, availablePieces ?: listOf())
    }

    private fun switchPlayer() {
        currentPlayer = if (currentPlayer == "Jogador 1") "Jogador 2" else "Jogador 1"
        txtCurrentPlayer.text = "Vez do $currentPlayer"
    }

    private fun disableBoard() {
        for (i in 0 until 3) {
            for (j in 0 until 3) {
                val child = gridBoard.getChildAt(i * 3 + j)
                if (child is ImageView) { // Verifica se é um ImageView
                    child.isEnabled = false // Desabilita a interação
                }
            }
        }
    }


    private fun checkWinner(): Boolean {
        for (i in 0 until 3) {
            if (board[i][0].first == currentPlayer && board[i][1].first == currentPlayer && board[i][2].first == currentPlayer) {
                disableBoard()
                showWinnerDialog("$currentPlayer venceu!")
                return true
            }
            if (board[0][i].first == currentPlayer && board[1][i].first == currentPlayer && board[2][i].first == currentPlayer) {
                disableBoard()
                showWinnerDialog("$currentPlayer venceu!")
                return true
            }
        }
        if (board[0][0].first == currentPlayer && board[1][1].first == currentPlayer && board[2][2].first == currentPlayer) {
            disableBoard()
            showWinnerDialog("$currentPlayer venceu!")
            return true
        }
        if (board[0][2].first == currentPlayer && board[1][1].first == currentPlayer && board[2][0].first == currentPlayer) {
            disableBoard()
            showWinnerDialog("$currentPlayer venceu!")
            return true
        }
        return false
    }


    private fun showWinnerDialog(message: String) {
        val dialog = androidx.appcompat.app.AlertDialog.Builder(this)
            .setTitle("Resultado")
            .setMessage(message)
            .setPositiveButton("Reiniciar") { _, _ ->
                resetGame() // Reinicia o jogo
            }
            .setNegativeButton("Sair") { _, _ ->
                finish() // Sai para a tela inicial
            }
            .setCancelable(false) // Impede que o diálogo seja fechado tocando fora dele
            .create()

        dialog.show()
    }

    private fun resetGame() {
        // Reinicia o tabuleiro
        for (i in 0 until 3) {
            for (j in 0 until 3) {
                board[i][j] = Pair("", PieceSize.PEQUENO)
                val button = gridBoard.getChildAt(i * 3 + j) as ImageView
                button.setImageResource(0)
                button.isEnabled = true
            }
        }

        // Reinicia as peças disponíveis
        piecesAvailable["Jogador 1"] = mutableListOf(
            PieceSize.PEQUENO, PieceSize.PEQUENO, PieceSize.PEQUENO,
            PieceSize.MEDIO, PieceSize.MEDIO, PieceSize.MEDIO,
            PieceSize.GRANDE, PieceSize.GRANDE, PieceSize.GRANDE
        )
        piecesAvailable["Jogador 2"] = mutableListOf(
            PieceSize.PEQUENO, PieceSize.PEQUENO, PieceSize.PEQUENO,
            PieceSize.MEDIO, PieceSize.MEDIO, PieceSize.MEDIO,
            PieceSize.GRANDE, PieceSize.GRANDE, PieceSize.GRANDE
        )

        // Resetar jogador atual
        currentPlayer = "Jogador 1"
        txtCurrentPlayer.text = "Vez do $currentPlayer"

        // Atualizar o Spinner
        updateSpinner()
    }


}

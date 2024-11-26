package com.example.jokenpo

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Referências aos TextViews
        val playerChoiceText = findViewById<TextView>(R.id.userChoice)
        val cpuChoiceText = findViewById<TextView>(R.id.cpuChoice)
        val playerScoreText = findViewById<TextView>(R.id.playerScore)
        val cpuScoreText = findViewById<TextView>(R.id.CPUScore)

        // Referências aos botões
        val btnPedra = findViewById<Button>(R.id.btnpedra)
        val btnPapel = findViewById<Button>(R.id.btnpapel)
        val btnTesoura = findViewById<Button>(R.id.btntesoura)
        val btnStart = findViewById<Button>(R.id.btnStart)


        var playerScore = 0
        var cpuScore = 0

        // determinar o vencedor
        fun determineWinner(playerChoice: String, cpuChoice: String): String {
            return when {
                playerChoice == cpuChoice -> "Empate"
                (playerChoice == "Pedra" && cpuChoice == "Tesoura") ||
                        (playerChoice == "Papel" && cpuChoice == "Pedra") ||
                        (playerChoice == "Tesoura" && cpuChoice == "Papel") -> "Jogador"
                else -> "CPU"
            }
        }

        // atualizar a escolha da CPU
        fun getCpuChoice(): String {
            val choices = listOf("Pedra", "Papel", "Tesoura")
            return choices[Random.nextInt(choices.size)]
        }

        // ação para cada botão
        fun playGame(playerChoice: String) {
            val cpuChoice = getCpuChoice()
            cpuChoiceText.text = cpuChoice
            playerChoiceText.text = playerChoice

            val winner = determineWinner(playerChoice, cpuChoice)

            when (winner) {
                "Jogador" -> {
                    playerScore++
                    playerScoreText.text = playerScore.toString()
                }
                "CPU" -> {
                    cpuScore++
                    cpuScoreText.text = cpuScore.toString()
                }
            }
        }

        // configurando os botões
        btnPedra.setOnClickListener { playGame("Pedra") }
        btnPapel.setOnClickListener { playGame("Papel") }
        btnTesoura.setOnClickListener { playGame("Tesoura") }

        // reiniciar o jogo
        btnStart.setOnClickListener {
            playerScore = 0
            cpuScore = 0
            playerScoreText.text = "0"
            cpuScoreText.text = "0"
            playerChoiceText.text = ""
            cpuChoiceText.text = ""
        }
    }
}

import model.Card
import model.Equipment
import model.Monster
import model.Player
import service.equipmentService
import service.monsterService
import service.playerSevice
import service.readFile
import java.io.File

fun main() {

    val inputStream = File("cartas.csv").inputStream()
    val cards: List<Card> = readFile(inputStream)
    

    val p1 = Player("José")
    val p2 = Player("Pedro")

    val playerService = playerSevice()
    val monsterService = monsterService()
    val equipmentService = equipmentService()

    playerService.distributeCards(listOf(p1, p2), cards.toMutableList())

    var currentPlayer = p1
    while (true) {
        // Verifica se algum jogador perdeu
        if (p1.score <= 0 || p2.score <= 0) {
            val winner = if (p1.score > 0) p1 else p2
            println("${winner.playerName} caused an attack that destroyed the opponent!")
            println("The game is over! ${winner.playerName} won!")
            break // Sai do loop e finaliza o jogo
        }

        // Mostrar informações do jogo
        println("\n${currentPlayer.playerName}'s turn")
        println("Cards in hand:")
        currentPlayer.hand.forEach { card ->
            println("Name: ${card.name}, Attack: ${card.attack}, Defense: ${card.defense}")
        }
        println("Monsters in the field:")
        currentPlayer.positionedMonsters.forEach { monster ->
            println("Name: ${monster.name}, Attack: ${monster.attack}, Defense: ${monster.defense}, State: ${monster.state}")
        }

        println("\n${currentPlayer.playerName} Score: ${currentPlayer.score}")
        // Mostrar as opções do jogador
        println("\nChoose an option:")
        println("1 - Put a monster on the field")
        println("2 - Equip a monster")
        println("3 - Attack the opponent")
        println("4 - Change a monster's state (offensive/defensive)")
        println("5 - Pass the turn")

        // Obter a opção do jogador
        val option = readln().toInt()

        when (option) {
            1 -> {
                // Colocar um monstro no campo
                println("Choose the monster from your hand to put on the field:")
                currentPlayer.hand.forEachIndexed { index, card ->
                    println("${index + 1} - ${card.name} - ${card.description} - ${card.attack} - ${card.defense}")
                }

                val cardIndex = readln().toInt() - 1
                if (cardIndex in currentPlayer.hand.indices) {
                    val card = currentPlayer.hand[cardIndex]
                    if (card is Monster) {
                        println("Choose the monster's state (offensive or defensive):")
                        val state = readln()
                        if (state == "offensive" || state == "defensive") {
                            // Posiciona o monstro no campo
                            card.state = state
                            playerService.positionMonster(currentPlayer, card, state)
                            // Remove a carta da mão do jogador depois de posicioná-la
                            currentPlayer.hand.removeAt(cardIndex)
                            drawCard(currentPlayer, cards.toMutableList())
                         } else {
                            println("Invalid status! Use 'offensive' or 'defensive'.")
                        }
                    } else {
                        println("Selected card is not a monster.")
                    }
                } else {
                    println("Invalid option. Select a valid card from your hand.")
                }
                currentPlayer = if (currentPlayer == p1) p2 else p1
            }
            2 -> {
                // Equipar um monstro
                println("Choose the monster from your hand to equip:")
                currentPlayer.positionedMonsters.forEachIndexed { index, monster ->
                    println("${index + 1} - ${monster.name} - ${monster.description} - ${monster.attack} - ${monster.defense}")
                }
                val monsterIndex = readln().toInt() - 1
                if (monsterIndex in currentPlayer.positionedMonsters.indices) {
                    val monster = currentPlayer.positionedMonsters[monsterIndex]
                    println("Choose equipment from your hand to equip the monster:")
                    currentPlayer.hand.forEachIndexed { index, card ->
                        if (card is Equipment) {
                            println("${index + 1} - ${card.name}")
                        }
                    }
                    val equipmentIndex = readln().toInt() - 1
                    if (equipmentIndex in currentPlayer.hand.indices && currentPlayer.hand[equipmentIndex] is Equipment) {
                        val equipment = currentPlayer.hand[equipmentIndex] as Equipment
                        equipmentService.equipMonster(currentPlayer, monster, equipment)
                        drawCard(currentPlayer, cards.toMutableList())
                    } else {
                        println("Equipment not found.")
                    }
                } else {
                    println("Invalid option.")
                }
                currentPlayer = if (currentPlayer == p1) p2 else p1
            }
            3 -> {
                // Realizar ataque
                val opponent = if (currentPlayer == p1) p2 else p1
                if (opponent.positionedMonsters.isEmpty()) {
                    println("The opponent has no monsters on the board. Choose one of your monsters to deal direct damage:")
                    currentPlayer.positionedMonsters.forEachIndexed { index, monster ->
                        println("${index + 1} - ${monster.name} (State: ${monster.state}, Attack: ${monster.attack}), Defense: ${monster.defense}")
                    }
                    val attackerIndex = readln().toInt() - 1
                    if (attackerIndex in currentPlayer.positionedMonsters.indices) {
                        val attacker = currentPlayer.positionedMonsters[attackerIndex]
                        monsterService.attackMonster(attacker, null, currentPlayer, opponent)
                    } else {
                        println("Invalid option.")
                    }
                } else {
                    println("Choose one of your monsters to carry out the attack:")
                    currentPlayer.positionedMonsters.forEachIndexed { index, monster ->
                        println("${index + 1} - ${monster.name} (State: ${monster.state}, Attack: ${monster.attack}), Defense: ${monster.defense}")
                    }
                    val attackerIndex = readln().toInt() - 1
                    if (attackerIndex in currentPlayer.positionedMonsters.indices) {
                        val attacker = currentPlayer.positionedMonsters[attackerIndex]
                        println("Choose your opponent's monster to defend:")
                        opponent.positionedMonsters.forEachIndexed { index, monster ->
                            println("${index + 1} - ${monster.name} (State: ${monster.state}, Attack: ${monster.attack}), Defense: ${monster.defense}")
                        }
                        val defenderIndex = readln().toInt() - 1
                        if (defenderIndex in opponent.positionedMonsters.indices) {
                            val defender = opponent.positionedMonsters[defenderIndex]
                            monsterService.attackMonster(attacker, defender, currentPlayer, opponent)
                            drawCard(currentPlayer, cards.toMutableList())
                        } else {
                            println("Invalid option.")
                        }
                    } else {
                        println("Invalid option.")
                    }
                }
                currentPlayer = if (currentPlayer == p1) p2 else p1
            }
            4 -> {
                // Mudar o estado de um monstro
                println("Choose the monster from your hand or field to change its state:")
                (currentPlayer.positionedMonsters + currentPlayer.hand).forEachIndexed { index, card ->
                    if (card is Monster) {
                        println("${index + 1} - ${card.name} - ${card.description} - ${card.attack} - ${card.defense}")
                    }
                }
                val monsterIndex = readln().toInt() - 1
                if (monsterIndex in (currentPlayer.positionedMonsters + currentPlayer.hand).indices) {
                    val monster = (currentPlayer.positionedMonsters + currentPlayer.hand)[monsterIndex] as Monster
                    monsterService.changeMonsterState(currentPlayer, monster)
                    drawCard(currentPlayer, cards.toMutableList())
                } else {
                    println("Invalid option.")
                }
            }
            5 -> {
                // Passar a vez
                drawCard(currentPlayer, cards.toMutableList())
                currentPlayer = if (currentPlayer == p1) p2 else p1
            }
            else -> {
                println("Invalid option.")
            }
        }
    }
}
// Função para comprar uma nova carta do baralho
fun drawCard(player: Player, deck: MutableList<Card>) {
    if (deck.isNotEmpty()) {
        // Pega uma carta aleatória do baralho
        val randomCard = deck.random()
        deck.remove(randomCard) // Remove a carta do baralho
        player.hand.add(randomCard)
        println("${player.playerName} bought the card: ${randomCard.name}")
    } else {
        println("The deck is empty! No cards were drawn.")
    }
}



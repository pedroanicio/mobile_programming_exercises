package service

import model.Card
import model.Monster
import model.Player

class playerSevice {

    val purchasedCards: MutableMap<Player, Boolean> = mutableMapOf()

    fun purchaseCard(player: Player, card: Card, collection: MutableList<Card>){
        if (purchasedCards[player] == true){
            println("You have already purchased a card in this turn. ")
            return
        }
        if (player.hand.size >= 10){
            println("Player ${player.playerName} hand is full")
            return
        }

        if (collection.isEmpty()){
            println("No more cards to purchase")
            return
        }

        val purchasedCard = collection.removeAt(0)
        player.hand.add(purchasedCard)
        purchasedCards[player] = true
    }


    fun distributeCards(players: List<Player>, collection: MutableList<Card>, quantity: Int = 5){
        for(player in players){
            repeat(quantity){
                if (collection.isNotEmpty()){
                    val card = collection.random()
                    addPlayersHand(player, card)
                    collection.remove(card)
                } else {
                    println("No more cards to distribute")
                }
            }
            println("Player ${player.playerName} hand: ${player.hand.map { it.name }}")
        }
    }

    private fun addPlayersHand(player: Player, card: Card){
        if (player.hand.size < 10){
            player.hand.add(card)
            println("Card ${card.name}, ${card.description}, ${card.type} [${card.attack}, ${card.defense}] added to ${player.playerName} hand")
        } else {
            println("Player ${player.playerName} hand is full")
        }
    }

    fun positionMonster(player: Player, monster: Monster, state: String){
        if (player.positionedMonsters.size < 5){
            player.positionedMonsters.add(monster)
            monster.state = state
            monster.changeValue()
            println("Monster ${monster.name} positioned in ${player.playerName} field")
        } else {
            println("Player ${player.playerName} field is full")
        }
    }

    fun discardCard(player: Player, card: Card){
        if (player.hand.contains(card)){
            player.hand.remove(card)
            println("Card ${card.name} removed from ${player.playerName} hand")
        } else {
            println("Card ${card.name} not found in ${player.playerName} hand")
        }
    }


}
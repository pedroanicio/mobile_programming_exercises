package model

class Player(
    val playerName: String,
    var score: Int = 10000
){
    val hand: MutableList<Card> = mutableListOf();  // no maximo 10
    val positionedMonsters: MutableList<Monster> = mutableListOf(); // no maximo 5

}
package model

abstract class Card(
    val name: String,
    val description: String,
    var attack: Int,
    var defense: Int,
    val type: String
){
    open fun showInfo(){
        println("Card: $name - Description: $description - Attack: $attack - Defense: $defense (Type: $type)")
    }
}
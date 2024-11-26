package model

class Monster(
    name: String,
    description: String,
    attack: Int,
    defense: Int
) : Card(name, description, attack, defense, "monstro"){

    var state: String = "defensive" //initial state

    var value: Int = 0;

    fun changeValue(){
        if (state == "defensive"){
            value = defense;
        } else {
            value = attack;
        }
    }


    fun changeState(){
        if (state == "offensive"){
            state = "defensive";
            println("Monster state changed to $state")
        } else {
            state = "offensive";
            println("Monster state changed to $state")
        }
    }



    override fun showInfo(){
        super.showInfo()
        println("State: $state")
    }

}
package model

class Equipment(
    name: String,
    description: String,
    attack: Int = 0,
    defense: Int = 0
) : Card(name, description, attack, defense, "equipamento"){

    override fun showInfo(){
        super.showInfo()
    }

    fun equipMonster(monster: Monster){
        if (attack > 0){
            monster.attack += attack
            println("Equipment $name applied. ${monster.name} attack increased in $attack")
        }
        if (defense > 0){
            monster.defense += defense
            println("Equipment $name applied. ${monster.name} defense increased in $defense")
        }
    }

    fun unequipMonster(monster: Monster){
        if (attack > 0){
            monster.attack -= attack
            println("Equipment $name removed. ${monster.name} attack decreased in $attack")
        }
        if (defense > 0){
            monster.defense -= defense
            println("Equipment $name removed. ${monster.name} defense decreased in $defense")
        }
    }

}
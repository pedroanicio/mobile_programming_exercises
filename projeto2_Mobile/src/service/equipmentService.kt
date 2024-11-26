package service

import model.Equipment
import model.Monster
import model.Player

class equipmentService {

    private val usedEquipment: MutableMap<String, Boolean> = mutableMapOf()

    fun equipMonster(player: Player, monster: Monster, equipment: Equipment){
        if (usedEquipment[player.playerName] == true){
            println("You have already used an equipment in this turn. ")
            return
        }
        if(player.hand.contains(equipment)){
            equipment.equipMonster(monster)
            player.hand.remove(equipment)
            usedEquipment[player.playerName] = true
            println("Equipment ${equipment.name} applied to ${monster.name}")
        } else {
            println("Equipment ${equipment.name} not found in ${player.playerName} hand")
        }
    }

    fun unequipMonster(player: Player, monster: Monster, equipment: Equipment){
        if (usedEquipment[player.playerName] == true){
            println("You have already used an equipment in this turn. ")
            return
        }
        equipment.unequipMonster(monster)
        player.hand.add(equipment)
        usedEquipment[player.playerName] = true
        println("Equipment ${equipment.name} removed from ${monster.name}")
    }

    fun finishTurn(player: Player){
        usedEquipment[player.playerName] = false
        println("${player.playerName} turn finished")
    }

}
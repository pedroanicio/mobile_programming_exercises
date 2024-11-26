package service

import model.Monster
import model.Player

class monsterService {

    private val monstersThatAttacked: MutableSet<Monster> = mutableSetOf()

    public fun changeMonsterState(player: Player, monster: Monster) {
        monster.changeState()
        println("${player.playerName}'s monster ${monster.name} changed state to ${monster.state}.")
    }

    fun attackMonster(
        attacker: Monster,
        defender: Monster?,
        attackingPlayer: Player,
        defendingPlayer: Player
    ) {
        // Verificar se o monstro já atacou nesta rodada
        if (monstersThatAttacked.contains(attacker)) {
            println("${attacker.name} has already attacked this turn.")
            return
        }

        // Verificar se o monstro está em estado de ataque
        if (attacker.state != "offensive") {
            println("${attacker.name} cannot attack because it is in defensive state.")
            return
        }

        if (defender == null) {
            performDirectAttack(attacker, attackingPlayer, defendingPlayer)
        } else {
            performMonsterAttack(attacker, defender, attackingPlayer, defendingPlayer)
        }

        // Registrar que o monstro atacou nesta rodada
        monstersThatAttacked.add(attacker)
    }

    private fun performDirectAttack(attacker: Monster, attackingPlayer: Player, defendingPlayer: Player) {
        if (defendingPlayer.positionedMonsters.isNotEmpty()) {
            println("${attacker.name} cannot attack directly because ${defendingPlayer.playerName} has monsters on the field.")
            return
        }

        val damage = attacker.attack
        defendingPlayer.score -= damage
        println("${attackingPlayer.playerName}'s ${attacker.name} attacked directly, causing $damage damage!")
    }

    private fun performMonsterAttack(
        attacker: Monster,
        defender: Monster,
        attackingPlayer: Player,
        defendingPlayer: Player
    ) {
        if (attacker.state != "offensive") {
            println("${attacker.name} não pode atacar porque está em modo defensivo.")
            return
        }

        if (defender == null) {
            // Dano direto
            val damage = attacker.attack
            defendingPlayer.score -= damage
            println("${attackingPlayer.playerName}'s ${attacker.name} causou $damage de dano direto a ${defendingPlayer.playerName}!")
            }
            else {
                if (defender.state == "offensive") {
                    val damage = attacker.attack - defender.attack
                    if (damage > 0) {
                        defendingPlayer.score -= damage
                        defendingPlayer.positionedMonsters.remove(defender)
                        println("${attackingPlayer.playerName}'s ${attacker.name} destroyed ${defender.name}, causing $damage damage!")
                    } else {
                        println("${attacker.name} failed to destroy ${defender.name}.")
                    }
                } else if (defender.state == "defensive") {
                    if (attacker.attack > defender.defense) {
                        defendingPlayer.positionedMonsters.remove(defender)
                        println("${attackingPlayer.playerName}'s ${attacker.name} destroyed ${defender.name} in defense mode.")
                    } else {
                        val damage = defender.defense - attacker.attack
                        attackingPlayer.score -= damage
                        println("${attacker.name} failed to destroy ${defender.name} and received $damage damage!")
                    }
                }
            }

        }

    fun resetTurn() {
        monstersThatAttacked.clear()
        println("Turn reset: all monsters can attack again.")
    }

}

package service

import model.Card
import model.Equipment
import model.Monster
import java.io.InputStream

fun readFile(inputStream: InputStream): List<Card> {
    val cards = mutableListOf<Card>()

    inputStream.bufferedReader().use { reader ->
        reader.lineSequence().forEach { line ->
            val parts = line.split(";")
            val name = parts[0].trim()
            val description = parts[1].trim()
            val attack = parts[2].trim().toIntOrNull() ?: 0
            val defense = parts[3].trim().toIntOrNull() ?: 0
            val type = parts[4].trim()

            when (type) {
                "monstro" -> {
                    cards.add(Monster(name, description, attack, defense))
                }
                "equipamento" -> {
                    cards.add(Equipment(name, description, attack, defense))
                }
                else -> {
                    println("Tipo de carta desconhecido: $type")
                }
            }
        }
    }
    return cards
}
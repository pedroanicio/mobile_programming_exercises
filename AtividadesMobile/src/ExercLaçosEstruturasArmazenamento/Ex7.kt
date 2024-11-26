package ExercLaçosEstruturasArmazenamento

fun main() {

    println("Informe a distância total percorrida (em metros):")
    val distanciaTotsl = readLine()!!.toInt()

    val numAltitudes = distanciaTotsl / 250
    val altitudes = mutableListOf<Int>()

    println("Informe as altitudes a cada 250 metros (separadas por espaço):")
    val altitudeInput = readLine()!!.split(" ").map { it.toInt() }

    for (altitude in altitudeInput) {
        altitudes.add(altitude)
    }

    altitudes.add(altitudes[0])

    var distanciaTotalSubindo = 0
    for (i in 0 until altitudes.size - 1) {
        if (altitudes[i + 1] > altitudes[i]) {
            distanciaTotalSubindo += 250
        }
    }

    println("Distância total subindo: $distanciaTotalSubindo metros")

}
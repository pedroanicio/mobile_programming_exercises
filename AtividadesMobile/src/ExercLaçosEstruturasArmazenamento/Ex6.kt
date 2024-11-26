package ExercLaçosEstruturasArmazenamento

fun main() {
    var maiorValor: Int = Int.MIN_VALUE
    var segundoMaior: Int = Int.MAX_VALUE

    println("Digite 20 números: ")

    for(i in 1..20){
        print("Número $i: ")
        val numero = readln().toInt()

        if (numero >= maiorValor){
            segundoMaior = maiorValor;
            maiorValor = numero
        } else if (numero > segundoMaior && numero != maiorValor){
            segundoMaior = numero;
        }
    }

    println("O maior número é: $maiorValor")
    println("O segundo maior número é: $segundoMaior")
}
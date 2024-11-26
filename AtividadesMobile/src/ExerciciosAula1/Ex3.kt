package ExerciciosAula1

fun main() {
    //R, P, S
    println("Digite r, s ou p")
    val jogador1: String = readln();

    println("Digite r, s ou p")
    val jogador2: String = readln();

    rockPaperScissors(jogador1, jogador2)

}

fun rockPaperScissors (j1: String, j2: String){
    if (j1.equals(j2)){
        println("Empate!")
    } else if (j1.equals("r") && j2.equals("s")
        || j1.equals("p") && j2.equals("r") || j1.equals("s") && j2.equals("p")){
        println("Jogador 1 venceu!")
    } else {
        println("Jogador 2 venceu!")
    }
}
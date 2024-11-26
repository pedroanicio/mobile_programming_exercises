package ExercLaçosEstruturasArmazenamento

fun main() {
    var valorTotal: Double = 0.0;
    var media: Double;
    var qtdePessoas: Int = 0;
    var acabou: String = "n"; // s ou n

    while (acabou.equals("n")){
        var idade: Double;
        println("Qual a quantidade de moradores na casa? ")
        var qtdePorCasa: Int = readln().toInt();
        qtdePessoas += qtdePorCasa;

        for (i in 1..qtdePorCasa){
            print("Idade da pessoa $i: ")
            idade = readln().toDouble();
            valorTotal += idade;
        }

        do {
            println("As casas acabaram? s ou n")
            acabou = readln()
            if (acabou != "s" && acabou != "n") {
                println("Opção inválida. Por favor, digite 's' ou 'n'.")
            }
        } while (acabou != "s" && acabou != "n")

    }

    media = (valorTotal/qtdePessoas);

    println("A média total da idade é $media");
}
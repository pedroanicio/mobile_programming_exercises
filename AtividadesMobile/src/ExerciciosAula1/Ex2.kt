package ExerciciosAula1

fun main() {
    val notas10 = 2
    val notas5 = 10
    val notas1 = 10

    val valorCompra: Int = readlnOrNull()?.toInt() ?: 0;

    calcularNumeroNotas(notas10, notas5, notas1, valorCompra);
}

fun calcularNumeroNotas(notas10: Int, notas5: Int, notas1: Int, valorCompra: Int){
    var qtdeNotas10: Int = 0;
    var qtdeNotas5: Int = 0;
    var qtdeNotas1: Int = 0;

    var notas10Dec = notas10;
    var notas5Dec = notas5;
    var notas1Dec = notas1;



    var quantiaCarteira: Int = (notas10*10)+(notas5*5)+(notas1);

    if (valorCompra > quantiaCarteira){
        println("ERRO: Valor alto demais")
    } else {
        var valorRestante = valorCompra
        do {
            if (valorRestante >= 10 && notas10Dec>0){
                valorRestante -= 10;
                notas10Dec--;
                qtdeNotas10++;
            } else if (valorRestante >= 5 && notas5Dec>0){
                valorRestante -= 5;
                notas5Dec--;
                qtdeNotas5++;
            } else if (valorRestante >= 1 && notas1Dec>0){
                valorRestante -= 1;
                notas1Dec--;
                qtdeNotas1++;
            }
        } while (valorRestante>0)

    }
    println("Notas de R$10: $qtdeNotas10 \nNotas de R$5: $qtdeNotas5 \nNotas de R$1: $qtdeNotas1")
}
package ExerciciosAula1

fun main() {
    var inicio = 13;
    var final = 18;

    calcularHoras(inicio, final);

}

fun calcularHoras(a: Int, b:Int){
    val soma: Int
    if (b>a){
        soma = b - a;
    } else {
        soma = 24 - (a - b);
    }
    println("A quantidade total de horas da partida foi: $soma horas");
}
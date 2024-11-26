package ExerciciosAula1

fun main() {
    val cpf: String = readln();

    val cpfValidado: String = validaCpf(cpf).toString();
    println(cpfValidado)

    if (cpfValidado == cpf){
        println("CPF Válido")
    } else {
        println("CPF Inválido")
    }

}

fun validaCpf(cpf: String): String?{
    // verificar se tem exatamente 11 digitos
    if (cpf.length == 11){
        return calcularPrimeiroDigitoVerificador(cpf.dropLast(2))
    } else {
        println("CPF inválido!")
        return null;
    }
}

fun calcularPrimeiroDigitoVerificador(cpf: String): String{
    var valor: Int = 10;
    var somaTotal: Int = 0;
    for (digito: Char in cpf){
        val digitoInt = Character.getNumericValue(digito);
        somaTotal += (digitoInt * valor);
        valor--;

    }

    return calcularSegundoDigitoVerificador(cpf, calculaResto(somaTotal));

}

fun calcularSegundoDigitoVerificador(cpf: String, primeiroDigitoVerificador: Int): String{
    var newCpf = cpf + primeiroDigitoVerificador;
    val segundoDigitoVerificador: Int;
    var valor: Int = 11;
    var somaTotal: Int = 0;

    for (digito: Char in newCpf){
        val digitoInt = Character.getNumericValue(digito);
        somaTotal += (digitoInt * valor);
        valor--;
    }

    newCpf += calculaResto(somaTotal);

    return newCpf;
}

fun calculaResto(somaTotal: Int): Int{
    val resto: Int = somaTotal % 11;
    var digitoVerificador: Int;
    if (resto == 0 || resto == 1){
        digitoVerificador = 0;
    } else {
        digitoVerificador = 11 - resto;
    }

    return digitoVerificador;
}
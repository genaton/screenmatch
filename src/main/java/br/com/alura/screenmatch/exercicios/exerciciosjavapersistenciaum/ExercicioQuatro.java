package br.com.alura.screenmatch.exercicios.exerciciosjavapersistenciaum;

// 4 - Implemente um método que verifica se uma frase é um palíndromo. Um palíndromo é uma palavra/frase que, quando lida de trás pra frente, é igual à leitura normal. Um exemplo:

public class ExercicioQuatro {

    public static void main(String[] args) {
        System.out.println(ehPalindromo("Socorram me subi no onibus em marrocos")); // Saída: true
        System.out.println(ehPalindromo("Java")); // Saída: false
        System.out.println(ehPalindromo("Ana")); 
        System.out.println(ehPalindromo("Roma e amor")); 

    }

    public static boolean ehPalindromo(String palavra) {
        String semEspacos = palavra.replace(" ", "").toLowerCase();
        System.out.println(semEspacos);
        return new StringBuilder(semEspacos).reverse().toString().equalsIgnoreCase(semEspacos);
    }

}

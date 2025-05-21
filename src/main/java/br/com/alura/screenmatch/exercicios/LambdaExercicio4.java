package br.com.alura.screenmatch.exercicios;
@FunctionalInterface
interface Palindromo{
    boolean verificarPalindromo(String s);
}
public class LambdaExercicio4 {
    public static void main(String[] args) {
        Palindromo palindromo = s -> s.equals(new StringBuilder(s).reverse().toString());
        System.out.println(palindromo.verificarPalindromo("radar"));
        System.out.println(palindromo.verificarPalindromo("java"));
    }

}

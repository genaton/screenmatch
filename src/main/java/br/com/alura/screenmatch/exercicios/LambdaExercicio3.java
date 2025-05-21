package br.com.alura.screenmatch.exercicios;
// @FunctionalInterface
interface Transformador{
    String transformar(String s);
}
public class LambdaExercicio3 {
    public static void main(String[] args) {
        Transformador toUpperCase = s -> s.toUpperCase();
        System.out.println(toUpperCase.transformar("java"));
    }

}

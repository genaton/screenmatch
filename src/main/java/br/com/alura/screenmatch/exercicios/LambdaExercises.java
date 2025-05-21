package br.com.alura.screenmatch.exercicios;

@FunctionalInterface
interface Soma{
    int soma(int a, int b);
}

public class LambdaExercises {
    public static void main(String[] args) {
         Soma soma = (a, b) -> a+b;
        System.out.println(soma.soma(5,3));
    }

}

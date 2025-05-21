package br.com.alura.screenmatch.exercicios;
@FunctionalInterface
interface Multiplicacao{
    int multiplicacao(int a, int b);
}

public class LambdaExercicio1 {
    public static void main(String[] args) {
        
        Multiplicacao multiplicacao = (a,b) -> a * b;
        System.out.println(multiplicacao.multiplicacao(5,3));
    }

}

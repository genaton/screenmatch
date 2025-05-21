package br.com.alura.screenmatch.exercicios;

import java.util.*;

public class LambdaExercicio6 {
    public static void main(String[] args) {
        List<String> nomes = Arrays.asList("Lucas", "Maria", "João", "Ana");
        nomes.sort((a,b)-> a.compareTo(b));
        System.out.println(nomes);
    }

}

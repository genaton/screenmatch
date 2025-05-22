package br.com.alura.screenmatch.exercicios;

import java.util.Arrays;
import java.util.List;
// 4 - Dada a lista de números inteiros abaixo, calcule a soma dos quadrados dos números pares.
public class SomaDosQuadradosDosNumerosPares {

     public static void main(String[] args) {
        List<Integer> numeros = Arrays.asList(1, 2, 3, 4, 5, 6);
        
        int resultado = numeros.stream()
        .filter(n -> n % 2 == 0)
        .map(n -> n * n)
        .reduce(0, Integer::sum);

        System.out.println(resultado);
    }

}

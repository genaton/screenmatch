package br.com.alura.screenmatch.exercicios;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class OrdenaPrimos {
    public static void main(String[] args) {
        List<List<Integer>> listaDeNumeros = Arrays.asList(
                Arrays.asList(1, 2, 3, 4),
                Arrays.asList(5, 6, 7, 8),
                Arrays.asList(9, 10, 11, 12));

        List<Integer> resultado = listaDeNumeros.stream()
                .flatMap(List::stream)
                .filter(OrdenaPrimos::ehPrimo)
                .sorted()
                .collect(Collectors.toList());

        System.out.println(resultado);
    }

    private static boolean ehPrimo(int numero) {
        if (numero < 2)
            return false;
        for (int i = 2; i <= Math.sqrt(numero); i++) {
            if (numero % i == 0) {
                return false;
            }
        }
        return true;
    }

}

package br.com.alura.screenmatch.exercicios;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FiltraImpares {

    public static void main(String[] args) {
        List<Integer> numeros = Arrays.asList(1, 2, 3, 4, 5, 6);
        List<Integer> resultado = numeros.stream()
        .filter(n -> n % 2 != 0) // esta lógica é melhor do que n % 2 == 1 pois funciona tanto para nrs positivos quanto para negativos
        .map(n -> n * 2)
        .collect(Collectors.toList());

        System.out.println(resultado);

    }

}

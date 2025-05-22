package br.com.alura.screenmatch.exercicios;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import br.com.alura.screenmatch.model.Episodio;

// 1 - Dada a lista de números inteiros a seguir, encontre o maior número dela.
public class Max {
    public static void main(String[] args) {

        List<Integer> numeros = Arrays.asList(10, 20, 30, 40, 50);

        Optional<Integer> max = numeros.stream()
                .max(Integer::compare);
        max.ifPresent(System.out::println);

    }
}

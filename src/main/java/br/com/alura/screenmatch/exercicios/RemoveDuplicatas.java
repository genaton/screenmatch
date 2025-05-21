package br.com.alura.screenmatch.exercicios;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class RemoveDuplicatas {

    public static void main(String[] args) {
        List<String> palavras = Arrays.asList("apple", "banana", "apple","orange", "banana");
        List<String> resultado = palavras.stream()
            .distinct()
            .collect(Collectors.toList());
        System.out.println(resultado);
    }

}

package br.com.alura.screenmatch.exercicios;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ConcatenaArrayStrings {

    public static void main(String[] args) {
        List<String> nomes = Arrays.asList("Alice", "Bob", "Charlie");
        System.out.println(nomes);
        
        String agrupa = nomes.stream()
        .collect(Collectors.joining(", "));

        System.out.println(agrupa);
        

    }

}

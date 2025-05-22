package br.com.alura.screenmatch.exercicios;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

// 2 -Dada a lista de palavras (strings) abaixo, agrupe-as pelo seu tamanho. 

public class AgrupaStringPorTamanho {
    public static void main(String[] args) {
        List<String> palavras = Arrays.asList("java", "stream", "lambda", "code");

        Map<Integer, List<String>> agrupaPorTamanho = palavras.stream()
            .collect(Collectors.groupingBy(String::length));

            System.out.println(agrupaPorTamanho);

        

        
    }


}

package br.com.alura.screenmatch.exercicios;

import java.util.Arrays;
import java.util.List;

public class ConverteParaMaiuscula {
    public static void main(String[] args) {
        List<String> palavras = Arrays.asList("java", "stream", "lambda");

        palavras.stream()
        .forEach(p -> System.out.println( p.toUpperCase()))
        ;

        System.out.println("\nResolução Oficial do exercício: ");
        palavras.stream()
        .map(String::toUpperCase)
        .forEach(System.out::println);
        ;
        

    }

}

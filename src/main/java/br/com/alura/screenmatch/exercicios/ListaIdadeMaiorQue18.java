package br.com.alura.screenmatch.exercicios;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class ListaIdadeMaiorQue18 {
    public static void main(String[] args) {

        List<Pessoa> pessoas = Arrays.asList(
                new Pessoa("Alice", 22),
                new Pessoa("Bob", 17),
                new Pessoa("Charlie", 19));

        List<String> maiorDe18 = pessoas
                .stream()
                .filter(p -> p.getIdade() > 18)
                .map(Pessoa::getNome)
                .collect(Collectors.toList());
                System.out.println(maiorDe18);
    }

}

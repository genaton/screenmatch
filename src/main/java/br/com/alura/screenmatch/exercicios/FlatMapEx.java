package br.com.alura.screenmatch.exercicios;

import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

public class FlatMapEx {
    public static void main(String[] args) {
        List<List<String>> list = List.of(
  List.of("a", "b"),
  List.of("c", "d")
);
System.out.println(list);

Stream<String> stream = list.stream()
  .flatMap(Collection::stream);

stream.forEach(System.out::println);
    }

}

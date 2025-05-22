package br.com.alura.screenmatch.exercicios;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class AgrupaProdutoPorCategoria {


    public static void main(String[] args) {
        List<Produto> produtos = Arrays.asList(
            new Produto("Smartphone", 800.0, "Eletrônicos"),
            new Produto("Notebook", 1500.0, "Eletrônicos"),
            new Produto("Teclado", 200.0, "Eletrônicos"),
            new Produto("Cadeira", 300.0, "Móveis"),
            new Produto("Monitor", 900.0, "Eletrônicos"),
            new Produto("Mesa", 700.0, "Móveis")
        );

        Map<String, List<Produto>>  agrupaPorCategoria =produtos.stream()
                .collect(Collectors.groupingBy(Produto::getCategoria));

                System.out.println("Produtos agrupados por categoria: ");
                System.out.println(agrupaPorCategoria);
                
         Map<String, Long>  contagemPorCategoria =produtos.stream()
                .collect(Collectors.groupingBy(Produto::getCategoria, Collectors.counting()));

                System.out.println("\nQuantidade de Produtos agrupados por categoria: ");
                System.out.println(contagemPorCategoria);
                
                Map<String, Optional<Produto>>  maisCaroPorCategoria =produtos.stream()
                .collect(Collectors.groupingBy(Produto::getCategoria, 
                Collectors.maxBy(Comparator.comparingDouble(Produto::getPreco))));
                
                System.out.println("\nProdutos mais caros por agrupados por categoria: ");
                System.out.println(maisCaroPorCategoria);

                Map<String, Double> somaPrecoPorCategoria = produtos.stream()
                .collect(Collectors.groupingBy(Produto::getCategoria, 
                Collectors.summingDouble(Produto::getPreco)));

                System.out.println("\n Soma de preços por categoria: ");
                System.out.println(somaPrecoPorCategoria);

        
    }
}

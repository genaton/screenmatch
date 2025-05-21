package br.com.alura.screenmatch.exercicios;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FiltraOsTresProdutosMaisBaratos {
    public static void main(String[] args) {
        List<Produto> produtos = Arrays.asList(
            new Produto("Smartphone", 800.0, "Eletrônicos"),
            new Produto("Notebook", 1500.0, "Eletrônicos"),
            new Produto("Teclado", 200.0, "Eletrônicos"),
            new Produto("Cadeira", 300.0, "Móveis"),
            new Produto("Monitor", 900.0, "Eletrônicos"),
            new Produto("Mesa", 700.0, "Móveis")
        );

        List<String> produtosMaisBaratos = produtos.stream()
            .filter(p -> p.getPreco() < 1000 && p.getCategoria() == "Eletrônicos" )
            .sorted((p1, p2) -> Double.compare(p1.getPreco(), p2.getPreco()))
            .limit(3)
            .map(Produto::toString)
            .collect(Collectors.toList());

            System.out.println(produtosMaisBaratos);

            System.out.println("\nresposta oficial: ");
             List<Produto> produtosFiltrados = produtos.stream()
                                                  .filter(p -> p.getCategoria().equals("Eletrônicos")) // Filtrar pela categoria
                                                    .filter(p -> p.getPreco() < 1000)                   // Filtrar pelo preço
                                                    .sorted((p1, p2) -> Double.compare(p1.getPreco(), p2.getPreco())) // Ordenar pelo preço
                                                    .limit(3)                                           // Pegar os 3 primeiros
                                                    .collect(Collectors.toList());  

        System.out.println(produtosFiltrados);
        
    }

}

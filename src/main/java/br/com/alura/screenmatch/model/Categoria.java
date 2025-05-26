package br.com.alura.screenmatch.model;

import org.springframework.boot.autoconfigure.aop.AopAutoConfiguration;

public enum Categoria {

    ACAO("Action","Acao"),
    AVENTURA("Adventure", "Aventura"),
    COMEDIA("Comedy", "Comedia"),
    CRIME("Crime", "Crime"),
    DRAMA("Drama", "Drama"),
    ROMANCE("Romance", "Romance");

    private String categoriaOmdb;

    private String categoriaPortugues;

    Categoria(String categoriaOmdb, String categoriaPortugues){
        this.categoriaOmdb = categoriaOmdb;
        this.categoriaPortugues = categoriaPortugues;
    }

    public static Categoria fromString(String text) {
        for (Categoria categoria : Categoria.values()) {
            if (categoria.categoriaOmdb.equalsIgnoreCase(text)) {
                return categoria;
            }
        }
        throw new IllegalArgumentException("Nenhuma categoria encontrada para a string fornecida: " + text);
    }

      public static Categoria fromPortugues(String text) {
        for (Categoria categoria : Categoria.values()) {
            if (categoria.categoriaPortugues.equalsIgnoreCase(text)) {
                return categoria;
            }
        }
        throw new IllegalArgumentException("Nenhuma categoria encontrada para a string fornecida: " + text);
    }




}

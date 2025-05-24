package br.com.alura.screenmatch.model;

import org.springframework.boot.autoconfigure.aop.AopAutoConfiguration;

public enum Categoria {

    ACAO("Action"),
    AVENTURA("Adventure"),
    COMEDIA("Comedy"),
    CRIME("Crime"),
    DRAMA("Drama"),
    ROMANCE("Romance");

    private String categoriaOmdb;

    Categoria(String categoriOmdb){
        this.categoriaOmdb = categoriOmdb;
    }

    public static Categoria fromString(String text) {
        for (Categoria categoria : Categoria.values()) {
            if (categoria.categoriaOmdb.equalsIgnoreCase(text)) {
                return categoria;
            }
        }
        throw new IllegalArgumentException("Nenhuma categoria encontrada para a string fornecida: " + text);
    }




}

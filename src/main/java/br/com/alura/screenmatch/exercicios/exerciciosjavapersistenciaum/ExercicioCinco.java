package br.com.alura.screenmatch.exercicios.exerciciosjavapersistenciaum;

import java.util.Arrays;
import java.util.List;

// 5 - Implemente um método que recebe uma lista de e-mails (String) e retorna uma nova lista onde cada e-mail está convertido para letras minúsculas.

public class ExercicioCinco {

    public static void main(String[] args) {
        List<String> emails = Arrays.asList("TESTE@EXEMPLO.COM", "exemplo@Java.com ", "Usuario@teste.Com");
        System.out.println(converterEmails(emails));
        // Saída: ["teste@exemplo.com", "exemplo@java.com", "usuario@teste.com"]

    }

    public static List<String> converterEmails(List<String> emails) {

        return emails.stream()
        .map(email -> email.trim().toLowerCase()).toList();
        // Implementar aqui
    }

}

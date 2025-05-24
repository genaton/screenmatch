package br.com.alura.screenmatch.exercicios.exerciciosjavapersistenciaum;

// 3 - Implemente um método que recebe uma String representando um nome completo separado por espaços. 
// O método deve retornar o primeiro e o último nome após remover os espaços desnecessários.

public class ExercicioTres {

    public static void main(String[] args) {
        System.out.println(obterPrimeiroEUltimoNome("  João Carlos Silva   ")); // Saída: "João Silva"
        System.out.println(obterPrimeiroEUltimoNome("Maria   ")); // Saída: "Maria"
        System.out.println(obterPrimeiroEUltimoNome("Maria Angelica Fernandes de Silva e Sá  ")); // Saída: "Maria"

    }

    public static String obterPrimeiroEUltimoNome(String nomeCompleto) {
        String[] nomes = nomeCompleto.trim().split("\\s+");
        if(nomes.length ==1){
            return nomes[0];
        }
        return nomes[0] + " " + nomes[nomes.length - 1];

    }

}

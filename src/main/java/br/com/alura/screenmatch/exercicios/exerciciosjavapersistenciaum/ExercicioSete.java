package br.com.alura.screenmatch.exercicios.exerciciosjavapersistenciaum;

// 7 - Crie um enum Moeda com valores como DOLAR, EURO, REAL. Cada moeda deve ter uma taxa de conversão para reais. Adicione um método que recebe um valor em reais e retorna o valor convertido para a moeda.

public enum ExercicioSete {
    DOLAR(5.10),
    EURO(5.50),
    REAL(1.00);

    private final double taxa;

    ExercicioSete(double taxa){
        this.taxa = taxa;
    };
    public double converterPara(double valorEmReais){

        return valorEmReais /taxa;


    }
    public static void main(String[] args) {
        System.out.println(ExercicioSete.DOLAR.converterPara(100));
        System.out.println(ExercicioSete.EURO.converterPara(100));
    }

}

package br.com.alura.screenmatch.exercicios.exerciciosjavapersistenciaum;

// 6 - Crie um enum Mes que represente os meses do ano. Adicione um método que retorna o número de dias de cada mês, considerando anos não bissextos.

public enum ExercicioSeis {

    JANEIRO(31) ,
    FEVEREIRO(28),
    MARCO(31),
    ABRIL(30),
    MAIO(31),
    JUNHO(30),
    JULHO(31),
    AGOSTO(31),
    SETEMBRO(30),
    OUTUBRO(31),
    NOVEMBRO(30),
    DEZEMBRO(31);

    private final int dias;
    
    ExercicioSeis (int dias){
        this.dias = dias;
    }
    
    public int getNumeroDeDias(){
        return dias;
    }
    public static void main(String[] args) {
        ExercicioSeis mes = FEVEREIRO;

        System.out.println(mes.getNumeroDeDias());

        System.out.println(ExercicioSeis.FEVEREIRO.getNumeroDeDias()); 
        System.out.println(ExercicioSeis.MARCO.getNumeroDeDias()); 
        System.out.println(ExercicioSeis.JULHO.getNumeroDeDias()); 
    }

}

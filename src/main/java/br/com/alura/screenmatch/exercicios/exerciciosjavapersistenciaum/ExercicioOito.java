package br.com.alura.screenmatch.exercicios.exerciciosjavapersistenciaum;
// 8 - Crie um enum CodigoErro com valores de erros HTTP, como NOT_FOUND, BAD_REQUEST, INTERNAL_SERVER_ERROR. Cada valor deve ter um código numérico e uma descrição associados. Adicione métodos para acessar o código e a descrição. Dica: consulte o site https://http.cat/ para conhecer os vários erros HTTP e conseguir descrevê-los melhor.


public enum ExercicioOito {
    NOT_FOUND(404, "Recurso não encontrado"),
    BAD_REQUEST(400, "Requisição inválida"),
    INTERNAL_SERVER_ERROR(500, "Erro interno do servidor");


    private final String descricao;
    private final int codigo;

     ExercicioOito(int codigo, String descricao){
         this.codigo = codigo;
         this.descricao = descricao;
    }


    public int getCodigo(){
        return codigo;


    }

    public String getDescricao(){
        return descricao;

    }
    public static void main(String[] args) {
        System.out.println(ExercicioOito.NOT_FOUND.getCodigo());
        System.out.println(ExercicioOito.NOT_FOUND.getDescricao());
    }


}

package model;

public class Aula extends Conteudo {
    private int duracaoMinutos;

    public Aula(String titulo, String descricao, int duracaoMinutos) {
        super(titulo, descricao);
        this.duracaoMinutos = duracaoMinutos;
    }

    @Override
    public void exibir() {
        System.out.println("Aula: " + titulo + " - " + descricao + " (" + duracaoMinutos + " min)");
    }
}

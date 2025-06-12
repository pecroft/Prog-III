package model;

import java.util.ArrayList;
import java.util.List;

public class Curso extends Conteudo {
    private List<Aula> aulas = new ArrayList<>();
    private Instrutor instrutor;

    public Curso(String titulo, String descricao, Instrutor instrutor) {
        super(titulo, descricao);
        this.instrutor = instrutor;
    }

    public void adicionarAula(Aula aula) {
        aulas.add(aula);
    }

    public List<Aula> getAulas() {
        return aulas;
    }

    public Instrutor getInstrutor() {
        return instrutor;
    }

    @Override
    public void exibir() {
        System.out.println("Curso: " + titulo + " - " + descricao);
        instrutor.exibir();
        for (Aula aula : aulas) {
            aula.exibir();
        }
    }
}

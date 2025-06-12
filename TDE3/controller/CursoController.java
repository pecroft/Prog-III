package controller;

import model.*;

import java.util.ArrayList;
import java.util.List;

public class CursoController {
    private List<Curso> cursos = new ArrayList<>();

    public void adicionarCurso(Curso curso) throws CampoObrigatorioException {
        if (curso.getTitulo() == null || curso.getTitulo().isEmpty()) {
            throw new CampoObrigatorioException("O título do curso é obrigatório.");
        }
        cursos.add(curso);
    }

    public List<Curso> listarCursos() {
        return cursos;
    }

    public Curso buscarCursoPorNome(String nome) throws CursoNaoEncontradoException {
        return cursos.stream()
                .filter(c -> c.getTitulo().equalsIgnoreCase(nome))
                .findFirst()
                .orElseThrow(() -> new CursoNaoEncontradoException("Curso não encontrado: " + nome));
    }

    public void removerCurso(String nome) throws CursoNaoEncontradoException {
        Curso curso = buscarCursoPorNome(nome);
        cursos.remove(curso);
    }
}

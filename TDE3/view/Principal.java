package view;

import controller.CursoController;
import model.*;

import java.util.Scanner;

public class Principal {
    private static Scanner scanner = new Scanner(System.in);
    private static CursoController controller = new CursoController();

    public static void main(String[] args) {
        int opcao;

        do {
            System.out.println("\n1 - Cadastrar Curso");
            System.out.println("2 - Listar Cursos");
            System.out.println("3 - Buscar Curso");
            System.out.println("4 - Remover Curso");
            System.out.println("0 - Sair");
            System.out.print("Opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> cadastrarCurso();
                case 2 -> listarCursos();
                case 3 -> buscarCurso();
                case 4 -> removerCurso();
            }
        } while (opcao != 0);
    }

    private static void cadastrarCurso() {
        try {
            System.out.print("Título do curso: ");
            String titulo = scanner.nextLine();

            System.out.print("Descrição do curso: ");
            String descricao = scanner.nextLine();

            System.out.print("Nome do instrutor: ");
            String nomeInstrutor = scanner.nextLine();

            System.out.print("Email do instrutor: ");
            String emailInstrutor = scanner.nextLine();

            Instrutor instrutor = new Instrutor(nomeInstrutor, emailInstrutor);
            Curso curso = new Curso(titulo, descricao, instrutor);

            System.out.print("Quantas aulas deseja adicionar? ");
            int numAulas = scanner.nextInt();
            scanner.nextLine();

            for (int i = 0; i < numAulas; i++) {
                System.out.print("Título da aula: ");
                String tituloAula = scanner.nextLine();
                System.out.print("Descrição da aula: ");
                String descAula = scanner.nextLine();
                System.out.print("Duração (min): ");
                int duracao = scanner.nextInt();
                scanner.nextLine();
                curso.adicionarAula(new Aula(tituloAula, descAula, duracao));
            }

            controller.adicionarCurso(curso);
            System.out.println("Curso cadastrado com sucesso!");

        } catch (CampoObrigatorioException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private static void listarCursos() {
        for (Curso curso : controller.listarCursos()) {
            curso.exibir();
        }
    }

    private static void buscarCurso() {
        System.out.print("Nome do curso: ");
        String nome = scanner.nextLine();
        try {
            Curso curso = controller.buscarCursoPorNome(nome);
            curso.exibir();
        } catch (CursoNaoEncontradoException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void removerCurso() {
        System.out.print("Nome do curso: ");
        String nome = scanner.nextLine();
        try {
            controller.removerCurso(nome);
            System.out.println("Curso removido com sucesso.");
        } catch (CursoNaoEncontradoException e) {
            System.out.println(e.getMessage());
        }
    }
}

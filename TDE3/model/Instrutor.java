package model;

public class Instrutor implements Exibivel {
    private String nome;
    private String email;

    public Instrutor(String nome, String email) {
        this.nome = nome;
        this.email = email;
    }

    public void exibir() {
        System.out.println("Instrutor: " + nome + " - " + email);
    }
}

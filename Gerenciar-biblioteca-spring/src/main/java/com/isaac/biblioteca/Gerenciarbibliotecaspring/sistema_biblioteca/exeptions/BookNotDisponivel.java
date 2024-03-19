package com.isaac.biblioteca.Gerenciarbibliotecaspring.sistema_biblioteca.exeptions;

public class BookNotDisponivel extends RuntimeException{

    public BookNotDisponivel() {
        super("Livro não disponivel no momento.");
    }

    public BookNotDisponivel(String message) {
        super(message);
    }
}

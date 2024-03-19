package com.isaac.biblioteca.Gerenciarbibliotecaspring.sistema_biblioteca.exeptions;

public class BookNotFoundException extends RuntimeException{
    public BookNotFoundException(String message) {
        super(message);
    }
    public BookNotFoundException() {
        super("Livro não encontrado.");
    }
}

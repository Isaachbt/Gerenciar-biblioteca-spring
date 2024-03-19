package com.isaac.biblioteca.Gerenciarbibliotecaspring.security.exception;

public class UserNotFound extends RuntimeException{

    public UserNotFound() {
        super("Usuario não encontrado, tente novamente.");
    }

    public UserNotFound(String message) {
        super(message);
    }
}

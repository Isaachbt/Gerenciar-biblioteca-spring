package com.isaac.biblioteca.Gerenciarbibliotecaspring.security.exception;

public class UserExists extends RuntimeException{
    public UserExists() {
        super("Login encontrado.");
    }

    public UserExists(String message) {
        super(message);
    }
}

package com.isaac.biblioteca.Gerenciarbibliotecaspring.security.exception;

public class NotFound extends RuntimeException{

    public NotFound() {
        super();
    }

    public NotFound(String message) {
        super(message);
    }
}

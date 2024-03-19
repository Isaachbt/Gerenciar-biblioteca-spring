package com.isaac.biblioteca.Gerenciarbibliotecaspring.security.exception;

public class PasswordIncorreta extends RuntimeException{

    public PasswordIncorreta() {
        super("Password user incorreta.");
    }

    public PasswordIncorreta(String message) {
        super(message);
    }
}

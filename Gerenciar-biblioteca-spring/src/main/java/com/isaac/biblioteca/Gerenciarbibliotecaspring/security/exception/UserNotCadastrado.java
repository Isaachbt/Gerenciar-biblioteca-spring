package com.isaac.biblioteca.Gerenciarbibliotecaspring.security.exception;

public class UserNotCadastrado extends RuntimeException{

    public UserNotCadastrado() {
        super("Usuario não cadastrado");
    }

    public UserNotCadastrado(String message) {
        super(message);
    }
}

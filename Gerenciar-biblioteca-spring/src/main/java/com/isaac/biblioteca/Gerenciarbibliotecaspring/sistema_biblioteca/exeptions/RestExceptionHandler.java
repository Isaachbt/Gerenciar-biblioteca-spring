package com.isaac.biblioteca.Gerenciarbibliotecaspring.sistema_biblioteca.exeptions;

import com.isaac.biblioteca.Gerenciarbibliotecaspring.security.exception.PasswordIncorreta;
import com.isaac.biblioteca.Gerenciarbibliotecaspring.security.exception.UserExists;
import com.isaac.biblioteca.Gerenciarbibliotecaspring.security.exception.UserNotCadastrado;
import com.isaac.biblioteca.Gerenciarbibliotecaspring.security.exception.UserNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BookNotFoundException.class)
    private ResponseEntity<String> bookNotFound(BookNotFoundException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Livro não encontrado.");
    }

    @ExceptionHandler(BookNotDisponivel.class)
    private ResponseEntity<String> bookNotDisponivel(BookNotDisponivel e){
        return ResponseEntity.status(HttpStatus.CONFLICT).body("Livro reservado por outro usuário.");
    }


}

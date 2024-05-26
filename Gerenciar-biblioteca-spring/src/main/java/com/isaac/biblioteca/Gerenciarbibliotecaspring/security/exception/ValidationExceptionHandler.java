package com.isaac.biblioteca.Gerenciarbibliotecaspring.security.exception;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class ValidationExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationException(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult().getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

    @ExceptionHandler(PasswordIncorreta.class)
    private ResponseEntity<PasswordIncorreta> passwordIncorreta(PasswordIncorreta e){
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new PasswordIncorreta());
    }

    @ExceptionHandler(UserExists.class)
    private ResponseEntity<String> userExiste(UserExists e){
        return ResponseEntity.status(HttpStatus.CONFLICT).body("Usuario encontrado.");
    }

    @ExceptionHandler(NotFound.class)
    private ResponseEntity<String> userNotFound(NotFound e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado.");
    }

    @ExceptionHandler(UserNotCadastrado.class)
    private ResponseEntity<String> userNotCadastrado(UserNotCadastrado e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario não cadastrado");
    }


}

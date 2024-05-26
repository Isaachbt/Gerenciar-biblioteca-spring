package com.isaac.biblioteca.Gerenciarbibliotecaspring.sistema_biblioteca.service.modelo;

import com.isaac.biblioteca.Gerenciarbibliotecaspring.security.model.User;
import com.isaac.biblioteca.Gerenciarbibliotecaspring.sistema_biblioteca.model.Livros;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface UserService {

    User cadastrarUser(User user);
    Optional<User> findByLogin(String login);
    Optional<User> findByUser(Long id);
    Optional<User> perfils(Long id);
    User save(User user);

    ResponseEntity<Object> removerLivro(Long userId,Long idBook);
    Livros reservarLivroUser(Long idBook, Long idUser);


}

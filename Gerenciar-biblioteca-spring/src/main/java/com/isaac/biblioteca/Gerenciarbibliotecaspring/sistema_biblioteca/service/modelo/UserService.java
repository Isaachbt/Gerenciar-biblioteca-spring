package com.isaac.biblioteca.Gerenciarbibliotecaspring.sistema_biblioteca.service.modelo;

import com.isaac.biblioteca.Gerenciarbibliotecaspring.security.model.User;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface UserService {

    User cadastrarUser(User user);
    Optional<User> findByLogin(String login);
    Optional<User> findByUser(Long id);
    Optional<User> perfils(Long id);
    User save(User user);

    ResponseEntity<Object> removerLivro(Long userId,Long idBook);
    void reservarLivroUser(Long idBook,Long idUser);


}

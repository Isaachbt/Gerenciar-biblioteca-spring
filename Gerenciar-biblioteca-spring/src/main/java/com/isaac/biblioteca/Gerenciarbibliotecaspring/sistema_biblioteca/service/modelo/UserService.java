package com.isaac.biblioteca.Gerenciarbibliotecaspring.sistema_biblioteca.service.modelo;

import com.isaac.biblioteca.Gerenciarbibliotecaspring.sistema_biblioteca.model.User;

import java.util.Optional;

public interface UserService {

    User cadastrarUser(User user);
    Optional<User> findByLogin(String login);
    Optional<User> findByUser(Long id);
    Optional<User> perfils(Long id);
    User save(User user);


}

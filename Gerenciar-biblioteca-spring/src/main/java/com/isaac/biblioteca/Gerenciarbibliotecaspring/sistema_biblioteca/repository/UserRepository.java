package com.isaac.biblioteca.Gerenciarbibliotecaspring.sistema_biblioteca.repository;

import com.isaac.biblioteca.Gerenciarbibliotecaspring.sistema_biblioteca.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByLogin(String login);
    Optional<User> findByNome(String login);
}

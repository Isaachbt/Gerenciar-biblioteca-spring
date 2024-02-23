package com.isaac.biblioteca.Gerenciarbibliotecaspring.sistema_biblioteca.repository;

import com.isaac.biblioteca.Gerenciarbibliotecaspring.sistema_biblioteca.model.Livros;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BooksRepository extends JpaRepository<Livros, Long> {
    Optional<Livros> findByName(String name);

    Optional<Livros> findByAutor(String name);

    @Query("SELECT l FROM Livros l WHERE l.name = ?1 AND l.user.id = ?2")
    Optional<Livros> findByNameAndIdUser(String name, Long userId);

    @Query("SELECT l FROM Livros l WHERE l.user.id = ?1")
    Optional<List<Livros>> getAllBooksUser(Long id);

}

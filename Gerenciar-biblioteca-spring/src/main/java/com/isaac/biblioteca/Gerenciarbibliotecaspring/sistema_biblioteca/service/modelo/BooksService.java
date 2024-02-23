package com.isaac.biblioteca.Gerenciarbibliotecaspring.sistema_biblioteca.service.modelo;

import com.isaac.biblioteca.Gerenciarbibliotecaspring.sistema_biblioteca.model.Livros;

import java.util.List;
import java.util.Optional;

public interface BooksService {

    public List<Livros> getAll();
    Optional<Livros> findByBook(Long id);

    Livros save(Livros livros);

    Optional<Livros> livrosUser(String nome,Long id);

    Optional<List<Livros>> getAllBooksUser(Long id);



}

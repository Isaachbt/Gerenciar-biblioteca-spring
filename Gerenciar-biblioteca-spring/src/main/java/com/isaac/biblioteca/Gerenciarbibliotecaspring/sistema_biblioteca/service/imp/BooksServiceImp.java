package com.isaac.biblioteca.Gerenciarbibliotecaspring.sistema_biblioteca.service.imp;

import com.isaac.biblioteca.Gerenciarbibliotecaspring.sistema_biblioteca.model.Livros;
import com.isaac.biblioteca.Gerenciarbibliotecaspring.sistema_biblioteca.repository.BooksRepository;
import com.isaac.biblioteca.Gerenciarbibliotecaspring.sistema_biblioteca.service.modelo.BooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BooksServiceImp implements BooksService {

    @Autowired
    private BooksRepository repository;

    @Override
    public List<Livros> getAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Livros> findByBook(Long id) {
       return repository.findById(id);
    }

    @Override
    public Livros save(Livros livros) {
        return repository.save(livros);
    }

    @Override
    public Optional<Livros> livrosUser(String nome, Long id) {
        return repository.findByNameAndIdUser(nome,id);
    }

    @Override
    public Optional<List<Livros>> getAllBooksUser(Long id) {
        return repository.getAllBooksUser(id);
    }


    public Optional<Livros> findByName(String nome){
        return repository.findByName(nome);
    }
}

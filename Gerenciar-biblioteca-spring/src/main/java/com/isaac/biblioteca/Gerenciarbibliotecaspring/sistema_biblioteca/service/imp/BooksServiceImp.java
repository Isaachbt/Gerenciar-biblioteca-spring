package com.isaac.biblioteca.Gerenciarbibliotecaspring.sistema_biblioteca.service.imp;

import com.isaac.biblioteca.Gerenciarbibliotecaspring.security.exception.NotFound;
import com.isaac.biblioteca.Gerenciarbibliotecaspring.sistema_biblioteca.model.Livros;
import com.isaac.biblioteca.Gerenciarbibliotecaspring.sistema_biblioteca.repository.BooksRepository;
import com.isaac.biblioteca.Gerenciarbibliotecaspring.sistema_biblioteca.service.modelo.BooksService;
import com.isaac.biblioteca.Gerenciarbibliotecaspring.sistema_biblioteca.exeptions.BookNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BooksServiceImp implements BooksService {

    @Autowired
    private BooksRepository repository;

    @Override
    public List<Livros> getAll() {
        List<Livros> livro = repository.findAll();
        if (livro.isEmpty()) {
            throw new BookNotFoundException();
        }
        return repository.findAll();
    }

    @Override
    public Optional<Livros> findByBook(Long id) throws BookNotFoundException {
       Optional<Livros> livros = repository.findById(id);
       if (livros.isEmpty()) throw new BookNotFoundException("Não foi possivel encontrar o livro com o id: ");
       return livros;
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
    public List<Livros> getAllBooksUser(Long id) {
        return repository.getAllBooksUser(id);
    }

    public Optional<Livros> findByName(String nome){
        Optional<Livros> livros =  repository.findByName(nome);
        if (livros.isEmpty()) throw new BookNotFoundException("Livro ( "+nome+") não encontrado.");
        return livros;
    }
}

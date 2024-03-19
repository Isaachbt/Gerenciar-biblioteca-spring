package com.isaac.biblioteca.Gerenciarbibliotecaspring.sistema_biblioteca.service.imp;

import com.isaac.biblioteca.Gerenciarbibliotecaspring.security.exception.UserNotFound;
import com.isaac.biblioteca.Gerenciarbibliotecaspring.security.model.User;
import com.isaac.biblioteca.Gerenciarbibliotecaspring.sistema_biblioteca.exeptions.BookNotDisponivel;
import com.isaac.biblioteca.Gerenciarbibliotecaspring.sistema_biblioteca.exeptions.BookNotFoundException;
import com.isaac.biblioteca.Gerenciarbibliotecaspring.sistema_biblioteca.model.Livros;
import com.isaac.biblioteca.Gerenciarbibliotecaspring.sistema_biblioteca.repository.UserRepository;
import com.isaac.biblioteca.Gerenciarbibliotecaspring.sistema_biblioteca.service.modelo.BooksService;
import com.isaac.biblioteca.Gerenciarbibliotecaspring.sistema_biblioteca.service.modelo.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UserRepository repository;
    @Autowired
    private BooksService booksServiceImp;

    @Override
    public User cadastrarUser(User user) {
        return repository.save(user);
    }

    @Override
    public Optional<User> findByLogin(String login) {
        return repository.findByLogin(login);
    }

    @Override
    public Optional<User> findByUser(Long id) {
        return repository.findById(id);
    }

    @Override
    public Optional<User> perfils(Long id) {
        return repository.findById(id);
    }

    @Override
    public User save(User user) {
        return repository.save(user);
    }

    @Override
    public ResponseEntity<Object> removerLivro(Long userId, Long idBook) {
        Optional<User> userOptional = findByUser(userId);
        if (userOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado.");
        }

        User user = userOptional.get();
        List<Livros> listLivro = user.getLivros();

        Optional<Livros> livroOptional = listLivro.stream()
                .filter(livro -> livro.getId().equals(idBook))
                .findFirst();

        if (livroOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Livro não encontrado para este usuário.");
        }

        Livros livro = livroOptional.get();
        livro.setUser(null);
        livro.setDisponivel(true);

        listLivro.remove(livro);

        save(user);
        booksServiceImp.save(livro);

        return ResponseEntity.status(HttpStatus.OK).body("Livro removido com sucesso do usuário.");

    }

    @Override
    public void reservarLivroUser(Long idBook, Long idUser) throws BookNotFoundException, BookNotDisponivel,UserNotFound {
        User user = findByUser(idUser).orElseThrow(UserNotFound::new);

        Livros livro = booksServiceImp.findByBook(idBook).orElseThrow(BookNotFoundException::new);

        if (!livro.isDisponivel()) {
            throw new BookNotDisponivel();
        }

        livro.setUser(user);
        livro.setDisponivel(false);

        List<Livros> userLivros = user.getLivros();
        if (userLivros == null) {
            userLivros = new ArrayList<>();
        }
        userLivros.add(livro);
        user.setLivros(userLivros);

        save(user);
        booksServiceImp.save(livro);
    }


}

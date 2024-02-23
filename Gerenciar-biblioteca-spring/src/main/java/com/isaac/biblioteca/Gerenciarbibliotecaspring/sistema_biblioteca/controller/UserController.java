package com.isaac.biblioteca.Gerenciarbibliotecaspring.sistema_biblioteca.controller;

import com.isaac.biblioteca.Gerenciarbibliotecaspring.security.utils.AuthenticationFacade;
import com.isaac.biblioteca.Gerenciarbibliotecaspring.sistema_biblioteca.model.Livros;
import com.isaac.biblioteca.Gerenciarbibliotecaspring.sistema_biblioteca.model.User;
import com.isaac.biblioteca.Gerenciarbibliotecaspring.sistema_biblioteca.service.imp.BooksServiceImp;
import com.isaac.biblioteca.Gerenciarbibliotecaspring.sistema_biblioteca.service.imp.UserServiceImp;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/setup")
public class UserController {

    @Autowired
    private UserServiceImp serviceImp;

    @Autowired
    private BooksServiceImp booksServiceImp;
    @Autowired
    private AuthenticationFacade authenticationFacade;

    @GetMapping("/perfil")
    public ResponseEntity<Object> meuPerfil(){
        Optional<User> userLogado = serviceImp.findByUser(authenticationFacade.getCurrentUser().getId());

        if (userLogado.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario não encontrado, cadastre-se.");
        }
        User user = userLogado.get();

        Optional<List<Livros>> getAllBooks = booksServiceImp.getAllBooksUser(authenticationFacade.getCurrentUser().getId());

        if (getAllBooks.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario não tem livros na estante.");
        }
        user.setLivros(getAllBooks.get());
        return ResponseEntity.ok().body(user);
    }

    @GetMapping("/my-biblioteca")
    public ResponseEntity<List<Object>> findAllLivrosByUserId(){
        List<Livros> myBooks = booksServiceImp.getAllBooksUser(authenticationFacade.getCurrentUser().getId()).get();
        if (myBooks.isEmpty()){
            throw new RuntimeException("Estante vazia");
        }

        return ResponseEntity.ok(Collections.singletonList(myBooks));
    }

    @Transactional
    @DeleteMapping("/remove/{id_book}")
    public ResponseEntity<Object> removerLivro(@PathVariable(value = "id_book") @NotNull Long id_book) {
        Optional<User> userOptional = serviceImp.findByUser(authenticationFacade.getCurrentUser().getId());
        if (userOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado");
        }

        User user = userOptional.get();
        List<Livros> listLivro = user.getLivros();

        Optional<Livros> livroOptional = listLivro.stream()
                .filter(livro -> livro.getId().equals(id_book))
                .findFirst();

        if (livroOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Livro não encontrado para este usuário");
        }

        Livros livro = livroOptional.get();
        livro.setUser(null);
        livro.setDisponivel(true);

        listLivro.remove(livro);

        serviceImp.save(user);
        booksServiceImp.save(livro);

        return ResponseEntity.status(HttpStatus.OK).body("Livro removido com sucesso do usuário");
    }

    @PutMapping("/add/{id_book}")
    public ResponseEntity<Object> reservarLivro(@PathVariable(value = "id_book")@NotNull Long id_book){
        Optional<Livros> existsNameLivro = booksServiceImp.findByBook(id_book);

        if (existsNameLivro.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Livro não encontrado");
        }

        Livros livros = existsNameLivro.get();
        if (!livros.isDisponivel()){
            return ResponseEntity.status(HttpStatus.CONTINUE).body("Livro reservado por outro Usuário.");
        }

        Optional<User> userOptional = serviceImp.findByUser(authenticationFacade.getCurrentUser().getId());

        if (userOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado");
        }
        User user = userOptional.get();
        List<Livros> listLivro = user.getLivros();

        livros.setUser(user);
        livros.setDisponivel(false);

        if (listLivro == null){
            listLivro = new ArrayList<>();
        }
        listLivro.add(livros);
        user.setLivros(listLivro);


        booksServiceImp.save(livros);
        serviceImp.save(user);

        return ResponseEntity.status(HttpStatus.OK).body("Livro reservado com sucesso");
    }

}

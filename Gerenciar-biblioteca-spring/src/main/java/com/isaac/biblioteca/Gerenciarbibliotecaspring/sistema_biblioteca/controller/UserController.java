package com.isaac.biblioteca.Gerenciarbibliotecaspring.sistema_biblioteca.controller;

import com.isaac.biblioteca.Gerenciarbibliotecaspring.security.exception.UserNotFound;
import com.isaac.biblioteca.Gerenciarbibliotecaspring.security.utils.AuthenticationFacade;
import com.isaac.biblioteca.Gerenciarbibliotecaspring.sistema_biblioteca.exeptions.BookNotDisponivel;
import com.isaac.biblioteca.Gerenciarbibliotecaspring.sistema_biblioteca.exeptions.BookNotFoundException;
import com.isaac.biblioteca.Gerenciarbibliotecaspring.sistema_biblioteca.model.Livros;
import com.isaac.biblioteca.Gerenciarbibliotecaspring.security.model.User;
import com.isaac.biblioteca.Gerenciarbibliotecaspring.sistema_biblioteca.service.imp.BooksServiceImp;
import com.isaac.biblioteca.Gerenciarbibliotecaspring.sistema_biblioteca.service.imp.UserServiceImp;
import com.isaac.biblioteca.Gerenciarbibliotecaspring.sistema_biblioteca.service.modelo.BooksService;
import com.isaac.biblioteca.Gerenciarbibliotecaspring.sistema_biblioteca.service.modelo.UserService;
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
    private UserService serviceImp;
    @Autowired
    private BooksService booksServiceImp;
    @Autowired
    private AuthenticationFacade authenticationFacade;

    @GetMapping("/perfil")
    public ResponseEntity<Object> meuPerfil() {
        Long userId = authenticationFacade.getCurrentUser().getId();
        Optional<User> userLogado = serviceImp.findByUser(userId);

        if (userLogado.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado, cadastre-se.");
        }

        User user = userLogado.get();
        List<Livros> getAllBooks = booksServiceImp.getAllBooksUser(userId);

        if (getAllBooks.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body("Usuário não tem livros na estante.");
        }

        user.setLivros(getAllBooks);
        return ResponseEntity.ok().body(user);
    }

    @GetMapping("/my-biblioteca")
    public ResponseEntity<List<?>> findAllLivrosByUserId(){
            List<Livros> myBooks = booksServiceImp.getAllBooksUser(authenticationFacade.getCurrentUser().getId());

            if (myBooks.isEmpty()){
                return ResponseEntity.status(HttpStatus.OK).body(List.of("Estante de livros vazia, tente adcionar livros."));
            }
            return ResponseEntity.status(HttpStatus.OK).body(myBooks);
    }

    @Transactional
    @DeleteMapping("/remove/{id_book}")
    public ResponseEntity<Object> removerLivro(@PathVariable(value = "id_book") @NotNull Long idBook) {
        return serviceImp.removerLivro(authenticationFacade.getCurrentUser().getId(), idBook);
    }

    @PutMapping("/add/{id_book}")
    public ResponseEntity<Object> reservarLivro(@PathVariable(value = "id_book")@NotNull Long id_book){

        Long userId = authenticationFacade.getCurrentUser().getId();

            serviceImp.reservarLivroUser(id_book, userId);
            return ResponseEntity.ok("Livro reservado com sucesso.");

    }

}

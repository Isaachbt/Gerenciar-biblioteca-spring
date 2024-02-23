package com.isaac.biblioteca.Gerenciarbibliotecaspring.sistema_biblioteca.controller;

import com.isaac.biblioteca.Gerenciarbibliotecaspring.sistema_biblioteca.model.Livros;
import com.isaac.biblioteca.Gerenciarbibliotecaspring.sistema_biblioteca.model.User;
import com.isaac.biblioteca.Gerenciarbibliotecaspring.sistema_biblioteca.service.imp.BooksServiceImp;
import com.isaac.biblioteca.Gerenciarbibliotecaspring.sistema_biblioteca.service.imp.UserServiceImp;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("list-books/")
public class BooksController {

    @Autowired
    private BooksServiceImp serviceImp;

    @GetMapping("/allBooks")
    public ResponseEntity<List<Livros>> getAll(){
        return ResponseEntity.ok(serviceImp.getAll());
    }

    @GetMapping("/search/{name}")
    public ResponseEntity<Object> findOne(@PathVariable(value = "name")String name){
        Optional<Livros> booksSearc = serviceImp.findByName(name);
        if (booksSearc.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Livro não encontrado.");
        }
        return ResponseEntity.ok(booksSearc);
    }


}

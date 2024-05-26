package com.isaac.biblioteca.Gerenciarbibliotecaspring.sistema_biblioteca.controller;

import com.isaac.biblioteca.Gerenciarbibliotecaspring.security.utils.RespostaMSG;
import com.isaac.biblioteca.Gerenciarbibliotecaspring.sistema_biblioteca.model.Livros;
import com.isaac.biblioteca.Gerenciarbibliotecaspring.sistema_biblioteca.service.imp.BooksServiceImp;
import com.isaac.biblioteca.Gerenciarbibliotecaspring.sistema_biblioteca.exeptions.BookNotFoundException;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<Object> findOne(@PathVariable(value = "name") @NotBlank String name){
            serviceImp.findByName(name);
            return ResponseEntity.ok(new RespostaMSG("Livro ("+name+") reservado com sucesso."));
    }

}

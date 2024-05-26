package com.isaac.biblioteca.Gerenciarbibliotecaspring.sistema_biblioteca.service.imp;

import com.isaac.biblioteca.Gerenciarbibliotecaspring.security.enums.RoleEnum;
import com.isaac.biblioteca.Gerenciarbibliotecaspring.security.model.User;
import com.isaac.biblioteca.Gerenciarbibliotecaspring.sistema_biblioteca.model.Livros;
import com.isaac.biblioteca.Gerenciarbibliotecaspring.sistema_biblioteca.repository.BooksRepository;
import com.isaac.biblioteca.Gerenciarbibliotecaspring.sistema_biblioteca.repository.UserRepository;
import com.isaac.biblioteca.Gerenciarbibliotecaspring.sistema_biblioteca.service.modelo.BooksService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class UserServiceImpTest {

    @InjectMocks
    private BooksServiceImp booksServiceImp;

    @Autowired
    @InjectMocks
    private UserServiceImp userServiceImp;

    @BeforeEach
    void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("Usuario reservou um livro com sucesso")
    void reservarLivroUserSuccese() {
        var livro = new  Livros(1L,"tra","joao","falsfdj",false,false);
        String name = "Isaac";
        var user = new User(name,"Isaac@gmail.com","12345", RoleEnum.ADMIN);

        when(userServiceImp.reservarLivroUser(1L,1L)).thenReturn(livro);




    }

    @Test
    @DisplayName("Livro indiponivel para reserva")
    void reservarLivroUserNotSuccese() {

    }
}
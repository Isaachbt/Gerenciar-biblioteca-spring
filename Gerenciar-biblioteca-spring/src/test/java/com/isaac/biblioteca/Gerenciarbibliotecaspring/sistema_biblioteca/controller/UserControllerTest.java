package com.isaac.biblioteca.Gerenciarbibliotecaspring.sistema_biblioteca.controller;

import com.isaac.biblioteca.Gerenciarbibliotecaspring.security.utils.AuthenticationFacade;
import com.isaac.biblioteca.Gerenciarbibliotecaspring.sistema_biblioteca.model.Livros;
import com.isaac.biblioteca.Gerenciarbibliotecaspring.sistema_biblioteca.model.User;
import com.isaac.biblioteca.Gerenciarbibliotecaspring.sistema_biblioteca.service.imp.BooksServiceImp;
import com.isaac.biblioteca.Gerenciarbibliotecaspring.sistema_biblioteca.service.imp.UserServiceImp;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

class UserControllerTest {

    @Mock
    private UserServiceImp userService;

    @Mock
    private BooksServiceImp booksService;

    @Mock
    private AuthenticationFacade authenticationFacade;

    @InjectMocks
    private UserController userController;
    @Mock
    private BooksServiceImp booksServiceImp;

    public UserControllerTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindAllLivrosByUserId() {
        User user = new User();
        user.setId(1L);
        when(authenticationFacade.getCurrentUser()).thenReturn(user);

        List<Livros> livros = new ArrayList<>();

        livros.add(new Livros(1L,"Era do gelo","wirk","gelo",false,false));
        livros.add(new Livros(2L,"Marte","dorb","astrono",false,false));
        livros.add(new Livros(1L,"Selva","vik","mato",false,false));

        when(booksServiceImp.getAllBooksUser(1L)).thenReturn(Optional.of(livros));

        ResponseEntity<List<Object>> responseEntity = userController.findAllLivrosByUserId();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertEquals(1, responseEntity.getBody().size());
    }

}
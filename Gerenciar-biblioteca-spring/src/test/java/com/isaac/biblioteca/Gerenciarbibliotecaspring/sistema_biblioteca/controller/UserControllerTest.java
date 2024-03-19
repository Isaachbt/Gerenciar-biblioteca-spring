package com.isaac.biblioteca.Gerenciarbibliotecaspring.sistema_biblioteca.controller;

import com.isaac.biblioteca.Gerenciarbibliotecaspring.security.utils.AuthenticationFacade;
import com.isaac.biblioteca.Gerenciarbibliotecaspring.sistema_biblioteca.model.Livros;
import com.isaac.biblioteca.Gerenciarbibliotecaspring.security.model.User;
import com.isaac.biblioteca.Gerenciarbibliotecaspring.sistema_biblioteca.service.imp.BooksServiceImp;
import com.isaac.biblioteca.Gerenciarbibliotecaspring.sistema_biblioteca.service.imp.UserServiceImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class UserControllerTest {

    @Mock
    private UserServiceImp userService;
    @Mock
    private AuthenticationFacade authenticationFacade;
    @InjectMocks
    private UserController userController;
    @Mock
    private BooksServiceImp booksServiceImp;

    @BeforeEach
    public void setUp() {
        User user = new User();
        user.setId(1L);
        when(authenticationFacade.getCurrentUser()).thenReturn(user);
    }

    public UserControllerTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindAllLivrosByUserId() {

        List<Livros> livros = new ArrayList<>();

        livros.add(new Livros(1L,"Era do gelo","wirk","gelo",false,false));
        livros.add(new Livros(2L,"Marte","dorb","astrono",false,false));
        livros.add(new Livros(3L,"Selva","vik","mato",false,false));

        when(booksServiceImp.getAllBooksUser(1L)).thenReturn(livros);

        ResponseEntity<List<?>> responseEntity = userController.findAllLivrosByUserId();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertEquals(3, responseEntity.getBody().size());
    }

    @Test
    public void testRemoverLivro_Success() {

        Long idBook = 123L;
        ResponseEntity<Object> expectedResponse = ResponseEntity.ok().build();
        when(userService.removerLivro(eq(1L), eq(idBook))).thenReturn(expectedResponse);

        ResponseEntity<Object> responseEntity = userController.removerLivro(idBook);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());


        verify(userService, times(1)).removerLivro(eq(1L), eq(idBook));
    }
}
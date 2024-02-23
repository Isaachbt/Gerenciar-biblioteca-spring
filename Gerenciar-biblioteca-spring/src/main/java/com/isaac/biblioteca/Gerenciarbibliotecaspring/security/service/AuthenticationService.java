package com.isaac.biblioteca.Gerenciarbibliotecaspring.security.service;

import com.isaac.biblioteca.Gerenciarbibliotecaspring.sistema_biblioteca.model.LoginDto;
import com.isaac.biblioteca.Gerenciarbibliotecaspring.sistema_biblioteca.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AuthenticationService extends UserDetailsService {

    String obterToken(LoginDto dto);

    String validToken(String token);
    String gerarTokenJwt(User user);
}

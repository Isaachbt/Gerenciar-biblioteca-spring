package com.isaac.biblioteca.Gerenciarbibliotecaspring.security.service;

import com.isaac.biblioteca.Gerenciarbibliotecaspring.security.model.CadastrarDTO;
import com.isaac.biblioteca.Gerenciarbibliotecaspring.security.model.LoginDto;
import com.isaac.biblioteca.Gerenciarbibliotecaspring.security.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AuthenticationService extends UserDetailsService {

    String login(LoginDto dto);
    String obterToken(LoginDto dto);

    String validToken(String token);
    String gerarTokenJwt(User user);
    String getLoginFromExpiredToken(String token);
}

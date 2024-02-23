package com.isaac.biblioteca.Gerenciarbibliotecaspring.security.config;

import com.isaac.biblioteca.Gerenciarbibliotecaspring.security.service.AuthenticationService;
import com.isaac.biblioteca.Gerenciarbibliotecaspring.sistema_biblioteca.model.User;
import com.isaac.biblioteca.Gerenciarbibliotecaspring.sistema_biblioteca.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private UserRepository repository;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = extrairTokenHeader(request);

        if (token != null){
            String login = authenticationService.validToken(token);
            Optional<User> user = repository.findByLogin(login);

            var authentication = new UsernamePasswordAuthenticationToken(user.get(),null,user.get().getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request,response);
    }


    private String extrairTokenHeader(HttpServletRequest request){
        var authHeader = request.getHeader("Authorization");

        if (authHeader == null){
            return null;
        }

        if (!authHeader.split(" ")[0].equals("Bearer")){
            return null;
        }
        return authHeader.split(" ")[1];
    }
}

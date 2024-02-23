package com.isaac.biblioteca.Gerenciarbibliotecaspring.security.service.imp;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.isaac.biblioteca.Gerenciarbibliotecaspring.sistema_biblioteca.model.LoginDto;
import com.isaac.biblioteca.Gerenciarbibliotecaspring.security.service.AuthenticationService;
import com.isaac.biblioteca.Gerenciarbibliotecaspring.sistema_biblioteca.model.User;
import com.isaac.biblioteca.Gerenciarbibliotecaspring.sistema_biblioteca.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class AuthenticationServiceImp implements AuthenticationService {

    @Autowired
    private UserRepository repository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = repository.findByLogin(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado: " + username));

        if (user == null) {
            throw new UsernameNotFoundException("Credenciais inválidas para o usuário: " + username);
        }

        return user;
    }

    @Override
    public String obterToken(LoginDto dto) {
        var user = new User();
        BeanUtils.copyProperties(dto,user);
        return gerarTokenJwt(user);
    }

    @Override
    public String validToken(String token) {
        try{

            Algorithm algorithm = Algorithm.HMAC256("my-secret");
            return JWT.require(algorithm)
                    .withIssuer("gerenciar-biblioteca-spring")
                    .build()
                    .verify(token)
                    .getSubject();
        }catch (JWTVerificationException e){
            return "";
        }
    }

    @Override
    public String gerarTokenJwt(User user) {
        try{

            Algorithm algorithm = Algorithm.HMAC256("my-secret");
            return JWT.create()
                    .withIssuer("gerenciar-biblioteca-spring")
                    .withSubject(user.getLogin())
                    .withExpiresAt(gerarDateExpiracao())
                    .sign(algorithm);
        }catch (JWTCreationException e){
            throw new RuntimeException("Erro ao tentar gerar o token");
        }
    }

    private Instant gerarDateExpiracao(){
        return LocalDateTime.now()
                .plusHours(8)
                .toInstant(ZoneOffset.of("-03:00"));
    }


}

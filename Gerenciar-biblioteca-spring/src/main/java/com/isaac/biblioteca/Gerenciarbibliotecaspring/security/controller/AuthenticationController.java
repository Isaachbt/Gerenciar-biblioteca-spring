package com.isaac.biblioteca.Gerenciarbibliotecaspring.security.controller;

import com.isaac.biblioteca.Gerenciarbibliotecaspring.security.service.imp.AuthenticationServiceImp;
import com.isaac.biblioteca.Gerenciarbibliotecaspring.sistema_biblioteca.model.CadastrarDTO;
import com.isaac.biblioteca.Gerenciarbibliotecaspring.sistema_biblioteca.model.LoginDto;
import com.isaac.biblioteca.Gerenciarbibliotecaspring.sistema_biblioteca.model.User;
import com.isaac.biblioteca.Gerenciarbibliotecaspring.sistema_biblioteca.service.imp.UserServiceImp;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserServiceImp serviceImp;
    @Autowired
    private AuthenticationServiceImp authenticationServiceImp;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/cadastrar")
    public ResponseEntity<Object> cadastrarUser(@RequestBody @Valid CadastrarDTO dto){
        Optional<User> loginExists = serviceImp.findByLogin(dto.login());

        if (loginExists.isPresent()){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Login encontrado");
        }
        var passWordHas = passwordEncoder.encode(dto.password());
        var user = new User();
        BeanUtils.copyProperties(dto,user);
        user.setPassword(passWordHas);

        return ResponseEntity.ok(serviceImp.cadastrarUser(user));
    }

    @PostMapping("/login")
    public String auth(@RequestBody LoginDto dto){
        Optional<User> optionalUser = serviceImp.findByLogin(dto.login());

        if (optionalUser.isEmpty()) {
            throw new RuntimeException("User não encontrado");
        }

        User user = optionalUser.get();

        if (!passwordEncoder.matches(dto.password(), user.getPassword())) {
           throw new RuntimeException("Senha incorreta");
        }

        var authenticationToken = new UsernamePasswordAuthenticationToken(dto.login(), dto.password());
        authenticationManager.authenticate(authenticationToken);

        return authenticationServiceImp.obterToken(dto);
    }



}

package com.isaac.biblioteca.Gerenciarbibliotecaspring.security.controller;

import com.isaac.biblioteca.Gerenciarbibliotecaspring.security.exception.PasswordIncorreta;
import com.isaac.biblioteca.Gerenciarbibliotecaspring.security.exception.UserExists;
import com.isaac.biblioteca.Gerenciarbibliotecaspring.security.exception.UserNotFound;
import com.isaac.biblioteca.Gerenciarbibliotecaspring.security.service.AuthenticationService;
import com.isaac.biblioteca.Gerenciarbibliotecaspring.security.model.CadastrarDTO;
import com.isaac.biblioteca.Gerenciarbibliotecaspring.security.model.LoginDto;
import com.isaac.biblioteca.Gerenciarbibliotecaspring.security.model.User;
import com.isaac.biblioteca.Gerenciarbibliotecaspring.sistema_biblioteca.service.modelo.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

//    private final UserService serviceImp;
    @Autowired
    private  AuthenticationService authenticationService;
    @Autowired
    private  UserService userService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private  PasswordEncoder passwordEncoder;

    @PostMapping("/cadastrar")
    public ResponseEntity<Object> cadastrarUser(@RequestBody @Valid CadastrarDTO dto){

        Optional<User> loginExists = userService.findByLogin(dto.login());

        if (loginExists.isPresent()){
            throw new UserExists();
        }

        var passWordHas = passwordEncoder.encode(dto.password());
        var user = new User();
        BeanUtils.copyProperties(dto,user);
        user.setPassword(passWordHas);

        try{
            userService.cadastrarUser(user);
            return ResponseEntity.ok("User cadastrado com sucesso.");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Erro ao tentar criar User");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<Object> auth(@RequestBody LoginDto dto){
        User searchUser = userService.findByLogin(dto.login()).orElseThrow(UserNotFound::new);

        if (!passwordEncoder.matches(dto.password(), searchUser.getPassword())) {
            throw new PasswordIncorreta();
        }
        var authenticationToken = new UsernamePasswordAuthenticationToken(dto.login(), dto.password());
        authenticationManager.authenticate(authenticationToken);

        return ResponseEntity.ok(authenticationService.login(dto));
    }



}

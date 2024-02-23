package com.isaac.biblioteca.Gerenciarbibliotecaspring.sistema_biblioteca.model;

import com.isaac.biblioteca.Gerenciarbibliotecaspring.security.enums.RoleEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record LoginDto(
        @NotBlank String login,
        @NotBlank String password
){
}

package com.isaac.biblioteca.Gerenciarbibliotecaspring.sistema_biblioteca.model;

import com.isaac.biblioteca.Gerenciarbibliotecaspring.security.enums.RoleEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CadastrarDTO(
        @NotBlank String nome,
        @NotBlank String login,
        @NotBlank String password,
        @NotNull RoleEnum role
) {
}

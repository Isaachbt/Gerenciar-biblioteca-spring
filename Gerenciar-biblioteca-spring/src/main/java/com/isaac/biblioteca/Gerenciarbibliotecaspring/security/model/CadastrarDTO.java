package com.isaac.biblioteca.Gerenciarbibliotecaspring.security.model;

import com.isaac.biblioteca.Gerenciarbibliotecaspring.security.enums.RoleEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CadastrarDTO(
        @NotBlank(message = "Nome usuario não pode estar vazio")
        String nome,
        @NotBlank(message = "Login não pode estar vazio")
        String login,
        @NotBlank(message = "Password não deve estar vazia")
        @Size(min = 4, message = "A senha deve ter pelo menos 4 caracteres")
        String password,
        @NotNull RoleEnum role
) {
}

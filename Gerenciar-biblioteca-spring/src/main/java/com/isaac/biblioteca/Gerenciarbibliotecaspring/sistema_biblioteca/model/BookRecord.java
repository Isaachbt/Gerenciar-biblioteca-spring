package com.isaac.biblioteca.Gerenciarbibliotecaspring.sistema_biblioteca.model;

import jakarta.validation.constraints.NotBlank;

public record BookRecord(
        @NotBlank String name,
        @NotBlank String autor,
        @NotBlank String descricao
) {
}

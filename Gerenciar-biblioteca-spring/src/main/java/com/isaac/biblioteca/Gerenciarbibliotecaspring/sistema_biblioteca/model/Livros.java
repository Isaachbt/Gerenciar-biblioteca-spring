package com.isaac.biblioteca.Gerenciarbibliotecaspring.sistema_biblioteca.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.isaac.biblioteca.Gerenciarbibliotecaspring.security.model.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "TB_BOOKS")
public class Livros {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column
    private String name;
    @Column
    private String autor;
    @Column
    private String Descricao;
    @Column
    private boolean lido;
    @Column
    private boolean disponivel;

    @JsonIgnore
    @ManyToOne()
    private User user;

    public Livros(Long id, String name, String autor, String descricao, boolean lido, boolean disponivel) {
        this.id = id;
        this.name = name;
        this.autor = autor;
        Descricao = descricao;
        this.lido = lido;
        this.disponivel = disponivel;
    }

    public Livros(String name, String autor, String descricao, boolean lido, boolean disponivel) {
        this.name = name;
        this.autor = autor;
        Descricao = descricao;
        this.lido = lido;
        this.disponivel = disponivel;
    }

    public Livros() {
    }
}

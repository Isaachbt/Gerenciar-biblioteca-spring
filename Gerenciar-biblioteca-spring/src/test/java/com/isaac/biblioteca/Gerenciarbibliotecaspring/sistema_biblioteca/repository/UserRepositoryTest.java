package com.isaac.biblioteca.Gerenciarbibliotecaspring.sistema_biblioteca.repository;

import com.isaac.biblioteca.Gerenciarbibliotecaspring.security.enums.RoleEnum;
import com.isaac.biblioteca.Gerenciarbibliotecaspring.security.model.User;
import com.isaac.biblioteca.Gerenciarbibliotecaspring.sistema_biblioteca.model.Livros;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;



@DataJpaTest
@ActiveProfiles("test")
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;
    @Autowired
    EntityManager entityManager;


    @Test
    @DisplayName("Retornar sucesso se encontrar um usuario pelo nome")
    void findByNomeSuccess() {
         String name = "Isaac";
         List<Livros> livros = List.of(new Livros(1L,"tra","joao","falsfdj",false,false));
         var user = new User(name,"Isaac@gmail.com","12345", RoleEnum.ADMIN,livros);
         this.createUser(user);

        Optional<User> userRecup = this.userRepository.findByNome(name);

        assertThat(userRecup.isPresent()).isTrue();
    }

    @Test
    @DisplayName("Retornar sucesso se não encontrar um usuario pelo nome")
    void findByNomeNotSuccess() {
        String name = "Isaac";

        Optional<User> userRecup = this.userRepository.findByNome(name);

        assertThat(userRecup.isEmpty()).isTrue();
    }

    private User createUser(User userPass){
        this.entityManager.merge(userPass);
        return userPass;
    }

}
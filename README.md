# CRUD SPRING BOOT E SPRING SECURITY

-Projeto feito pensado em uma simulação de gerenciamento de biblioteca, contendo, (GET,PUT,POST,DELETE) nele o usuario podera se registrar com SPRING SECURITY e ter seu proprio perfil.
-Ao acessar o usuario podera, reservar um livro, pesquisar, excluir da sua biblioteca e etc

# Pré-requisitos
Certifique-se de ter o seguinte instalado em sua máquina:

-JDK 8 ou superior 

-Maven 

-Git 

# Configuração do Projeto
1 - Clone este repositório:
```
git clone https://github.com/Isaachbt/Gerenciar-biblioteca-spring.git
```

2 - Navegue ate o diretorio:
```
 cd Gerenciar-biblioteca-spring
```
3 - Execute o aplicativo Spring Boot:
```
mvn spring-boot:run
```

O aplicativo estará disponível em http://localhost:8080.

## Necessario ter

- PostgreSql/PgAdmin 4
- Postman ou Insomnia
- Recomendo Intellij

# Funcionalidades
Este aplicativo Spring CRUD oferece as seguintes funcionalidades:

Create(POST): Cadastrar usuairo.

Read(GET): Buscar livros do usuario, pesquisar livros por nome e pesquisar todos os livros .

Update(PUT): Reservar livros para o usuario.

Delete: Exclue livros reservados do usuario.

# Segurança
Este aplicativo implementa a segurança usando o Spring Security. As seguintes características de segurança estão em vigor:

- Autenticação de usuários.
- Autorização baseada em funções de usuário.
- Tokens com tempo.

- Acesso não autorizado: 403

# Tecnologias Utilizadas
- Spring Boot
- Spring Security
- Spring Data JPA
- Hateos
- Lombok
- PostgreSQL
- validation
- mockito

# Atualizações futuras
- Implementação em uma aplicação Android
- Mensageria

# Contribuição
Sinta-se à vontade para contribuir com novos recursos, correções de bugs ou melhorias de desempenho. Basta enviar um pull request!

# Licença
Este projeto é licenciado sob a [MIT License](LICENSE).

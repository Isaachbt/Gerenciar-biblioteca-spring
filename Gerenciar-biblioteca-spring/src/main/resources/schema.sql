-- Define a estrutura da tabela TB_USER
CREATE TABLE TB_USER (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    login VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(255) NOT NULL
);

-- Define a estrutura da tabela TB_BOOKS
CREATE SEQUENCE tb_books_seq START 1;
CREATE TABLE TB_BOOKS (
    id INT DEFAULT nextval('tb_books_seq') PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    autor VARCHAR(255) NOT NULL,
    descricao VARCHAR(255) NOT NULL,
    lido BOOLEAN,
    disponivel BOOLEAN
);





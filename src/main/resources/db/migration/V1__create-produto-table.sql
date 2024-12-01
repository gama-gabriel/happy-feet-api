CREATE SEQUENCE produto_seq;
CREATE TABLE IF NOT EXISTS produto (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    marca VARCHAR(100),
    keywords VARCHAR(100),
    categoria VARCHAR(100),
    preco DECIMAL(10, 2),
    descricao TEXT,
    tamanhos FLOAT[],
    genero VARCHAR(20)
);

CREATE SEQUENCE variante_seq;
CREATE TABLE variante (
    id SERIAL PRIMARY KEY,
    id_produto INTEGER REFERENCES produto(id),
    nome_cor VARCHAR(100),
    cor_hex VARCHAR(7)
);

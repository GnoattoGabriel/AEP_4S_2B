CREATE DATABASE microgestor;
USE microgestor;

CREATE TABLE produtos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100),
    preco DECIMAL(10,2),
    quantidade INT
);

CREATE TABLE clientes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100),
    email varchar(255),
    telefone VARCHAR(20)
);

CREATE TABLE vendas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    cliente_id INT,
    valor_total DECIMAL(10,2),
    data DATETIME,
    FOREIGN KEY (cliente_id) REFERENCES clientes(id)
);

select * from produtos;

truncate table produtos;
/**
 * Author:  lps10
 * Created: 19 de nov de 2020
 */
CREATE TABLE pessoa (
    id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(50) NOT NULL,
    ativo BOOLEAN NOT NULL,
    logradouro VARCHAR(100),
    numero VARCHAR(6),
    complemento VARCHAR(50),
    bairro VARCHAR(50),
    cep VARCHAR(15),
    cidade VARCHAR(50),
    estado VARCHAR(2)
) ENGINE = InnoDB DEFAULT CHARSET = utf8;
INSERT INTO pessoa (
        nome,
        ativo,
        logradouro,
        numero,
        complemento,
        bairro,
        cep,
        cidade,
        estado
    )
VALUES (
        'Lucas',
        false,
        'Rua X',
        '123',
        'Fundos',
        'Itaim',
        '123456789',
        'São Paulo',
        'SP'
    ),
    (
        'Camila',
        false,
        'Rua X',
        '123',
        'Fundos',
        'Itaim',
        '123456789',
        'São Paulo',
        'SP'
    );
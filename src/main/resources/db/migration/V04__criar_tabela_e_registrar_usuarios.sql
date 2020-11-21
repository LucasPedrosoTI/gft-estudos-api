CREATE TABLE usuario (
  id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
  email VARCHAR(100) UNIQUE NOT NULL,
  senha VARCHAR(255) NOT NULL,
  admin BOOLEAN NOT NULL
) ENGINE = InnoDB DEFAULT CHARSET = utf8;
INSERT INTO usuario (email, senha, admin)
VALUES (
    "admin@gft.com",
    "$2a$10$Cec8KPWfwi3DPaKgy1sTLO0lNJ8xkgqmQCYJjaRAD4eybIVgSiTnq",
    TRUE
  ),
  (
    "user@gft.com",
    "$2a$10$vQuPNPLmpLKdmJTArGIk4e7ne.K4J6kVTEUZH.ZK4IQf0X.d7GoWW",
    FALSE
  );
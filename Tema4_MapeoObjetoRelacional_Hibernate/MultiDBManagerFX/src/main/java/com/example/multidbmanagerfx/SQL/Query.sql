DROP DATABASE IF EXISTS CarManagementDB;
CREATE DATABASE CarManagementDB;
USE CarManagementDB;

CREATE TABLE coches (
  id INTEGER NOT NULL AUTO_INCREMENT,
  matricula VARCHAR(50) DEFAULT NULL,
  marca VARCHAR(50) DEFAULT NULL,
  modelo VARCHAR(50) DEFAULT NULL,
  tipo VARCHAR(50) DEFAULT NULL,
  PRIMARY KEY (id)
)  ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4 AUTO_INCREMENT=1 ;

INSERT INTO coches (matricula, marca, modelo, tipo)
VALUES ('1234AAA', 'Audi', 'Q5', 'SUV'),
 ('2345BBB', 'Ford', 'S-mMax', 'SUV'),
 ('9876CCC', 'Renault', 'Clio', 'SUV');



CREATE TABLE multas (
  id_multa INTEGER NOT NULL AUTO_INCREMENT,
  precio DOUBLE NOT NULL,
  fecha DATE DEFAULT NULL,
  matricula VARCHAR(7) NOT NULL,
  PRIMARY KEY (id_multa)
  ) ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4 AUTO_INCREMENT=1 ;

INSERT INTO multas (matricula, precio, fecha)
VALUES ('1234AAA', 200, '2023-12-12'),
       ('1234AAA', 700, '2023-12-13'),
       ('1234AAA', 50, '2023-12-14'),
       ('2345BBB', 250, '2024-12-01'),
        ('9876CCC', 100, '2024-11-21');

SELECT * FROM coches;
DROP DATABASE IF EXISTS Multas;
CREATE DATABASE Multas;
USE Multas;
CREATE TABLE coches (
  id integer NOT NULL AUTO_INCREMENT,
  matricula varchar(50) DEFAULT NULL,
  marca varchar(50) DEFAULT NULL,
  modelo varchar(50) DEFAULT NULL,
  tipo varchar(50) DEFAULT NULL,
  PRIMARY KEY (id)
)  ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4 AUTO_INCREMENT=1 ;

INSERT INTO coches (matricula, marca, modelo, tipo)
VALUES ('1234AAA', 'Audi', 'Q5', 'SUV'),
 ('2345BBB', 'Ford', 'S-mMax', 'SUV'),
 ('9876CCC', 'Renault', 'Clio', 'SUV');


CREATE TABLE IF NOT EXISTS multas (
  id_multa integer unsigned NOT NULL AUTO_INCREMENT,
  precio DOUBLE NOT NULL,
  fecha DATE DEFAULT NULL,
  matricula varchar(7) NOT NULL,
  PRIMARY KEY (id_multa)
  ) ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4 AUTO_INCREMENT=1 ;


INSERT INTO Multas (matricula, precio, fecha)
VALUES ('1234AAA', 200, '2023-12-12'),
       ('1234AAA', 700, '2023-12-13'),
       ('1234AAA', 50, '2023-12-14'),
       ('2345BBB', 250, '2024-12-01'),
        ('9876CCC', 100, '2024-11-21');
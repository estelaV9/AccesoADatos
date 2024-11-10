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
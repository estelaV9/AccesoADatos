drop database if exists usuarios;
create database usuarios;
use usuarios;

CREATE TABLE usuarios (
  email varchar(40) NOT NULL  PRIMARY KEY,
  contraseña varchar(64) DEFAULT NULL
) ENGINE=InnoDB  CHARSET=utf8mb4 ;

insert into usuarios values("juan@gmail.com",SHA2("juan",256));




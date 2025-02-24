DROP DATABASE IF EXISTS tutorial;
CREATE DATABASE  tutorial;
USE tutorial;
CREATE TABLE  usuarios (
  id integer NOT NULL AUTO_INCREMENT,
  nombre varchar(50) DEFAULT NULL,
  apellidos varchar(100) DEFAULT NULL,
  fechaNacimiento date DEFAULT NULL,
  PRIMARY KEY (id)
);
CREATE TABLE  profesor (
  id integer NOT NULL ,
  nombre varchar(50) DEFAULT NULL,
  ape1 varchar(100) DEFAULT NULL,
  ape2 varchar(100) DEFAULT NULL
);

SELECT * FROM profesor;
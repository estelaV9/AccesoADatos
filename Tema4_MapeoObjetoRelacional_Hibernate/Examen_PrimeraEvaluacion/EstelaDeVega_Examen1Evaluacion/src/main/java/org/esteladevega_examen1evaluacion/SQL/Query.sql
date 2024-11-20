DROP DATABASE IF  EXISTS ExamenEquipos;
CREATE DATABASE  ExamenEquipos;
USE ExamenEquipos;

CREATE TABLE  equipos
(
	idEquipo INT  PRIMARY KEY AUTO_INCREMENT,
	nombreEquipo VARCHAR(50) UNIQUE NOT NULL,
	patrocinador VARCHAR(50),
	categoria VARCHAR(50),
    sancionado BOOLEAN
);
insert into equipos values(1,"Robin Hood","Coca cola","Educacion Secundaria Obligatoria",false),
(2,"Carabobo","Red Bull","Formación Profesional",false),
(3,"Abuelos F.C","Mercadona","Profesores",true),
(4,"Rico Pollo","Carrefour","Bachillerato",false),
(5,"Correcaminos","Estrella Galicia","Formación Profesional",false);
CREATE TABLE  jugadores
(
	idJugador INT  PRIMARY KEY AUTO_INCREMENT,
	aliasJugador VARCHAR(50) NOT NULL,
	fechaNacimiento Date NOT NULL,
	id_equipo INT  REFERENCES equipos
);

insert into jugadores values
(1,"Juanito Bananas","1999-11-11",1),
(2,"Luis el Rubio","2005-11-10",2),
(3,"Jaimito el gorrilla","1999-11-11",1),
(4,"Ruben el pastelero","1995-11-15",5),
(5,"Daniel el guapo","1996-11-11",5),
(6,"Javier el pelao","1995-9-11",2),
(7,"David el nomo","1998-10-11",4),
(8,"Mario el pelota","1995-3-16",4),
(9,"Roberto monedero","2001-12-11",1),
(10,"Josete el flaco","1999-6-11",2);
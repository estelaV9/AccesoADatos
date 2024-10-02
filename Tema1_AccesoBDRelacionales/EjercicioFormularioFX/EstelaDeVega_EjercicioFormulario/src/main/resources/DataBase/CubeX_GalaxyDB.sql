/*REALIZADO POR : ESTELA DE VEGA MARTIN 2ºDAM*/
/**************DISENIO FISICO CUBE_x GALAXY***********/
DROP DATABASE IF EXISTS CubeX_GalaxyDB;
CREATE DATABASE CubeX_GalaxyDB;
USE CubeX_GalaxyDB;


/*TABLAS*/
/*TABLA CON TODOS LOS USUARIOS, SEAN SOCIOS O NO*/
DROP TABLE IF EXISTS CUBE_USERS;
CREATE TABLE CUBE_USERS (
    ID_USER INT AUTO_INCREMENT PRIMARY KEY,
    NAME_USER VARCHAR(45) UNIQUE NOT NULL,
    PASSWORD_USER VARCHAR(15) NOT NULL,
    /*EL USUARIO AL ENTRAR TENDRA UN NIVEL 0, CUANDO HAYA REALIZADO COMPRAS SE IRA INCREMENTANDO.
    POR CADA COMPRA SE LE SUMARA 25 EXP, CUANDO LLEGUE A 100 SE LE SUMARA UN NIVEL*/
    LEVEL_USER INT DEFAULT 0 NOT NULL,
    MAIL VARCHAR(100) NOT NULL,
    REGISTRATION_DATE DATE NOT NULL
);

insert into cube_users (NAME_USER, PASSWORD_USER, MAIL, REGISTRATION_DATE)
    VALUES ('a', 'Ps.contains(8)', 'a@gmail.com', current_date() );
insert into cube_users (NAME_USER, PASSWORD_USER, MAIL, REGISTRATION_DATE)
    VALUES ('b', 'Ps.contains(8)', 'b@gmail.com', current_date() );
insert into cube_users (NAME_USER, PASSWORD_USER, MAIL, REGISTRATION_DATE)
    VALUES ('n', 'Ps.contains(8)', 'n@gmail.com', current_date() );
insert into cube_users (NAME_USER, PASSWORD_USER, MAIL, REGISTRATION_DATE)
    VALUES ('m', 'Ps.contains(8)', 'm@gmail.com', current_date() );
insert into cube_users (NAME_USER, PASSWORD_USER, MAIL, REGISTRATION_DATE)
    VALUES ('j', 'Ps.contains(8)', 'j@gmail.com', current_date() );
insert into cube_users (NAME_USER, PASSWORD_USER, MAIL, REGISTRATION_DATE)
    VALUES ('k', 'Ps.contains(8)', 'k@gmail.com', current_date() );
insert into cube_users (NAME_USER, PASSWORD_USER, MAIL, REGISTRATION_DATE)
    VALUES ('l', 'Ps.contains(8)', 'l@gmail.com', current_date() );
insert into cube_users (NAME_USER, PASSWORD_USER, MAIL, REGISTRATION_DATE)
    VALUES ('s', 'Ps.contains(8)', 's@gmail.com', current_date() );
insert into cube_users (NAME_USER, PASSWORD_USER, MAIL, REGISTRATION_DATE)
    VALUES ('p', 'Ps.contains(8)', 'p@gmail.com', current_date() );
insert into cube_users (NAME_USER, PASSWORD_USER, MAIL, REGISTRATION_DATE)
    VALUES ('o', 'Ps.contains(8)', 'o@gmail.com', current_date() );

INSERT INTO CUBE_USERS (NAME_USER, PASSWORD_USER, MAIL, REGISTRATION_DATE)
	VALUES ('admin', 'Admin(9)', 'admin@admin.com', '2024-04-25'); -- USUARIO POR DEFECTO
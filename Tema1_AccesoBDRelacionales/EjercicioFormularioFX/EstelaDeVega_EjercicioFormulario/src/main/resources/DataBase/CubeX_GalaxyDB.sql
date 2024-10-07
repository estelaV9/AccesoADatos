/*REALIZADO POR : ESTELA DE VEGA MARTIN 2ºDAM*/
/**************DISENIO FISICO CUBE_x GALAXY***********/
DROP DATABASE IF EXISTS CubeX_GalaxyDB;
CREATE DATABASE CubeX_GalaxyDB;
USE CubeX_GalaxyDB;


/*TABLAS*/
/*TABLA USUARIOS*/
DROP TABLE IF EXISTS CUBE_USERS;
CREATE TABLE CUBE_USERS (
    ID_USER INT AUTO_INCREMENT PRIMARY KEY,
    NAME_USER VARCHAR(45) UNIQUE NOT NULL,
    PASSWORD_USER VARCHAR(15) NOT NULL,
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


SELECT * FROM cube_users WHERE name_user = "s" AND mail != "a@gmail.com";
select * from cube_users;

/*TABLA PRODUCTOS*/
DROP TABLE IF EXISTS CUBE_PRODUCT;
CREATE TABLE CUBE_PRODUCT (
    ID_PRODUCT INT AUTO_INCREMENT PRIMARY KEY,
    NAME_PRODUCT VARCHAR(45) NOT NULL,
    CATEGORY VARCHAR(25) NOT NULL,
    PRICE DECIMAL(5, 2) NOT NULL,
    NAME_OWNER_PRODUCT VARCHAR(45) NOT NULL
);

-- SELECT NAME_PRODUCT, CATEGORY, PRICE, OWNER_PRODUCT FROM CUBE_PRODUCT;
-- INSERTAR PRODUCTOS
INSERT INTO CUBE_PRODUCT (NAME_PRODUCT, CATEGORY, PRICE, NAME_OWNER_PRODUCT) VALUES
('Rubik 3x3 Gan 356 XS', '3x3', 39.99, (SELECT NAME_USER FROM CUBE_USERS WHERE ID_USER = 4)),
('MoYu WeiLong WR M 2020', '3x3', 29.99, (SELECT NAME_USER FROM CUBE_USERS WHERE ID_USER = 4)),
('QiYi MS 2x2', '2x2', 8.99, (SELECT NAME_USER FROM CUBE_USERS WHERE ID_USER = 3)),
('YJ YuLong Pyraminx V2 M', 'Pyraminx', 12.99, (SELECT NAME_USER FROM CUBE_USERS WHERE ID_USER = 2)),
('Yuxin Little Magic 4x4', '4x4', 13.99, (SELECT NAME_USER FROM CUBE_USERS WHERE ID_USER = 2)),
('ShengShou Megaminx', 'Megaminx', 14.99, (SELECT NAME_USER FROM CUBE_USERS WHERE ID_USER = 3)),
('X-Man Bell Magnetic Skewb', 'Skewb', 17.99, (SELECT NAME_USER FROM CUBE_USERS WHERE ID_USER = 7));

SELECT * FROM CUBE_PRODUCT;
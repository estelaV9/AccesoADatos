DROP
DATABASE IF EXISTS Inmuebles;
CREATE DATABASE Inmuebles;
Use Inmuebles;

CREATE TABLE Zonas
(
    idzona     int unsigned auto_increment primary key,
    nombrezona varchar(16) not null
);

INSERT INTO Zonas (nombrezona)
VALUES ('Delicias'),
       ('Parquesol'),
       ('Paseo Zorrilla'),
       ('Rondilla'),
       ('Pilarica');

CREATE TABLE Pisos
(
    idpiso        int unsigned auto_increment primary key,
    idzona        int unsigned,
    tipooperacion varchar(10),
    metros        int unsigned,
    precio        int unsigned,
    reservado     varchar(2),
    FOREIGN KEY (idzona)
        REFERENCES Zonas (idzona)
        ON DELETE CASCADE
);
INSERT INTO pisos (idzona, tipooperacion, metros, precio, reservado)
VALUES (1, "Compra", 100, 300000, "No"),
       (2, "Alquiler", 80, 450, "Si"),
       (3, "Alquiler", 90, 900, "Si"),
       (5, "Compra", 150, 120000, "No"),
       (4, "Compra", 75, 89000, "Si"),
       (4, "Alquiler", 70, 500, "No");

CREATE TABLE Usuarios
(
    username VARCHAR(255) PRIMARY KEY,
    password VARCHAR(255) NOT NULL
);

INSERT INTO Usuarios(username, password)
VALUES ('root', sha2('toor', 256));


-- Modificar el precio de compra de un inmueble, pero con la condición
-- de que este inmueble NO puede estar reservado (TODOS)
-- UPDATE Pisos SET precio = 3 WHERE idpiso = 1 AND reservado = "no";

SELECT * FROM Pisos where tipooperacion = "Alquiler" > 100 AND reservado = "no";
SELECT * FROM Pisos where tipooperacion = "Alquiler" AND precio < 500 AND reservado = "no";

SELECT * FROM pISOS;
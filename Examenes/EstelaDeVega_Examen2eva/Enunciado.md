# EXAMEN ACCESO A DATOS 21-2-2024

Crea una API que ofrezca unos servicios web para la gestión de vuelos. La aplicación tendrá una base de datos de vuelos donde almacenará: código de vuelo, origen, destino, precio, numero de escalas y compañía. Deberá ofrecer las siguientes operaciones:

- Búsqueda de vuelos, pudiendo filtrar por estos 3 campos origen, destino y numero de escalas.
- Búsqueda de vuelos, pudiendo filtrar por destino.
- Numero de vuelos que salen desde un origen.
- Registro de una compañía nueva.
- Dar de baja a una compañía por su id (No deberá haber inconsistencias en la BD al borrar el registro).
- Modificar el nombre de una compañía.

Deberéis restringir el acceso a la opción d, e y f. Para ello deberéis utilizar JWT y solo se podrán realizar esas operación si previamente se han validado  con:

        usuario: root 
        password: toor
        
Para ello es necesario que utilicéis la tabla Usuarios. Además, debéis documentar la API utilizando Swagger



## Script BD
```sql
DROP DATABASE IF EXISTS GestionVuelos; 
CREATE DATABASE  GestionVuelos;
USE GestionVuelos;
--   CREACIÓN DE LAS TABLAS COMPAÑIAS : 

CREATE TABLE Compañias
( idcompañia INTEGER PRIMARY KEY,
  nombrecompañia VARCHAR(50)
);


--   CREACIÓN DE LAS TABLAS VUELOS : 
CREATE TABLE Vuelos
( idvuelo VARCHAR(10) PRIMARY KEY,
  horasalida VARCHAR(15),
  origen VARCHAR(50),
  destino VARCHAR(50),
  precio FLOAT,
  numeroescalas INTEGER,
  idcompañia INTEGER,
  FOREIGN KEY (idcompañia) REFERENCES Compañias(idcompañia)
);

CREATE TABLE Usuarios(
   username VARCHAR(255) PRIMARY KEY,
   password VARCHAR(255) NOT NULL
);

INSERT INTO Usuarios(username, password) VALUES ('root', sha2('toor', 256));

--------------- LLENAR COMPAÑIAS: -------------------------------
INSERT INTO Compañias VALUES
(1,"Iberia"),
(2,"AirEuropa"),
(3,"Plus Ultra"),
(4,"American Airlines"),
(5,"Qatar Airways"),
(6,"Aerolineas Argentinas"),
(7,"AirFrance"),
(8,"ITA Airways"),
(9,"Vueling"),
(10,"Turkish Airlines"),
(11,"Vueling");

--------------- LLENAR VUELOS: -------------------------------
INSERT INTO Vuelos VALUES
('IB-SP-4567','27/03/99-10:30','PARIS','MADRID',20.99,1,1),
('AE-BA-46DC','28/03/99-12:30','ROMA','MADRID',25,1,2),
('PU-DC-4667','28/03/99-13:30','BRUSELAS','SEVILLA',58.63,2,3),
('AA-DC-347','29/03/99-13:35','VALENCIA','ROMA',36.99,1,4),
('QT-DC-438','30/03/99-09:20','VIENA','SEVILLA',90.25,1,5),
('AA-D7-347','30/03/99-13:35','BILBAO','Buenos Aires',425.78,2,6),
('AF-D5-347','01/04/99-13:35','ZARAGOZA','PARIS',30.36,1,7),
('QA-DC7-247','01/04/99-15:35','CORDOBA','EL CAIRO',235,2,5),
('IA-1256-5','04/04/99-10:30','MADRID','TOKIO',580.36,3,8),
('VU-DC9-233','01/04/99-17:35','VALENCIA','SOFÍA',125.45,1,9),
('TA-DC2-269','01/04/99-19:00','BARCELONA','MANILA',365.38,2,10),
('IB-98779','02/04/99-08:00','MADRID','LIMA',420.50,1,1),
('VU-DC2-269','02/04/99-12:00','MADRID','LA HAYA',1,100.36,9),
('AE-1289-9','02/04/99-14:30','BARCELONA','BONN',50.36,1,2),
('AA-1300-9','01/04/99-08:30','MADRID','LOS ANGELES',620.36,3,4);
```

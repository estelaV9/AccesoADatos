DROP DATABASE IF EXISTS apiAgricultores;
CREATE DATABASE apiAgricultores;
USE apiAgricultores;
CREATE TABLE Agricultor (
                            idagricultor int NOT NULL AUTO_INCREMENT PRIMARY KEY,
                            nombreagricultor varchar(50) DEFAULT NULL,
                            edadagricultor int

)  ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4 AUTO_INCREMENT=1 ;

INSERT INTO agricultor (nombreagricultor, edadagricultor)
VALUES ('Juan Lobato Pepinillos', 25),
       ('Ana Lechugas', 36),
       ('Jose el Cebollas', 30);


CREATE TABLE IF NOT EXISTS Granja (
                                      idgranja int unsigned NOT NULL AUTO_INCREMENT  PRIMARY KEY,
                                      tamañogranja int NOT NULL,
                                      idagricultor int ,
                                      FOREIGN KEY (id_Agricultor) REFERENCES Agricultor(id_Agricultor)

    ) ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4 AUTO_INCREMENT=1 ;


INSERT INTO Granja (tamañogranja, idagricultor)
VALUES (200, 1),
       ( 700, 2),
       (50, 1),
       (250, 3),
       ( 100, 2);
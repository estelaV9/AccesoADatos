drop database if exists compras;
create database compras;
use compras;

CREATE TABLE categorias(
    idcategoria integer      NOT NULL AUTO_INCREMENT,
    nombre      varchar(100) NOT NULL,
    PRIMARY KEY (idcategoria)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4;

INSERT INTO categorias
VALUES (1, 'Deliciosas frutas'),
       (2, 'Verdura'),
       (3, 'Pescados'),
       (4, 'Carnes'),
       (5, 'Lacteos'),
       (6, 'Postres');

CREATE TABLE productos(
    idproducto  integer      NOT NULL AUTO_INCREMENT,
    nombre      varchar(100) NOT NULL,
    descripcion varchar(300) DEFAULT NULL,
    stock       integer      DEFAULT NULL,
    idcategoria integer      DEFAULT NULL,
    FOREIGN KEY (idcategoria) REFERENCES categorias (idcategoria) ON UPDATE CASCADE,
    PRIMARY KEY (idproducto)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4;

INSERT INTO productos
VALUES (1, 'Manzana', 'Manzana saludable', 3, 1),
       (2, 'Pera', 'No ha salido de un olmo', 13, 1),
       (3, 'Brócoli', 'Verdura fractal', 5, 2),
       (4, 'Salmonete', 'Salmón chiquitín', 30, 3),
       (5, 'Tomate', 'Rojo como el pecado', 10, 2),
       (6, 'Coliflor', 'Blanca y radiante', 7, 2);


CREATE TABLE proveedores(
    idproveedor integer      NOT NULL AUTO_INCREMENT,
    nombre      varchar(100) NOT NULL,
    nif         varchar(15)  DEFAULT NULL,
    poblacion   varchar(150) DEFAULT NULL,
    PRIMARY KEY (idproveedor)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4;

INSERT INTO proveedores
VALUES (1, 'De fruta madre', 'B1231231', 'Lleida'),
       (2, 'La huerta de Pep', 'B716721', 'Girona'),
       (3, 'De la mar el mero', 'B2334231', 'Tarragona'),
       (4, 'La Huerta Paco', 'B11111', 'Segovia');

CREATE TABLE producto_proveedor(
    idproducto  integer NOT NULL,
    idproveedor integer NOT NULL,
    PRIMARY KEY (idproducto, idproveedor),
    CONSTRAINT fk_producto FOREIGN KEY (idproducto) REFERENCES productos (idproducto) ON UPDATE CASCADE,
    CONSTRAINT fk_proveedor FOREIGN KEY (idproveedor) REFERENCES proveedores (idproveedor) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO producto_proveedor
VALUES (1, 1), (1, 2), (2, 1), (3, 2), (4, 3);
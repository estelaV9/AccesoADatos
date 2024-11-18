DROP DATABASE IF EXISTS compras;
CREATE DATABASE compras;
USE compras;

-- TABLA CATEGORIAS
CREATE TABLE categorias (
    idcategoria INTEGER NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL,
    PRIMARY KEY (idcategoria)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- INSERTAR DATOS EN CATEGORIAS
INSERT INTO categorias (idcategoria, nombre)
VALUES (1, 'Deliciosas frutas'),
    (2, 'Verdura'),
    (3, 'Pescados'),
    (4, 'Carnes'),
    (5, 'Lacteos'),
    (6, 'Postres');

-- TABLA DE PRODUCTOS
CREATE TABLE productos (
    idproducto INTEGER NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL,
    descripcion VARCHAR(300) DEFAULT NULL,
    stock INTEGER DEFAULT NULL,
    idcategoria INTEGER DEFAULT NULL,
    FOREIGN KEY (idcategoria) REFERENCES categorias(idcategoria) ON UPDATE CASCADE,
    PRIMARY KEY (idproducto)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- INSERTAR DATOS EN PRODUCTOS
INSERT INTO productos (idproducto, nombre, descripcion, stock, idcategoria)
VALUES (1, 'Manzana', 'Manzana saludable', 3, 1),
    (2, 'Pera', 'No ha salido de un olmo', 13, 1),
    (3, 'Brócoli', 'Verdura fractal', 5, 2),
    (4, 'Salmonete', 'Salmón chiquitín', 30, 3),
    (5, 'Tomate', 'Rojo como el pecado', 10, 2),
    (6, 'Coliflor', 'Blanca y radiante', 7, 2);

-- TABLA DE PROVEEDORES
CREATE TABLE proveedores (
    idproveedor INTEGER NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL,
    nif VARCHAR(15) DEFAULT NULL,
    poblacion VARCHAR(150) DEFAULT NULL,
    PRIMARY KEY (idproveedor)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- INSERTAR DATOS EN PROVEEDORES
INSERT INTO proveedores (idproveedor, nombre, nif, poblacion)
VALUES (1, 'De fruta madre', 'B1231231', 'Lleida'),
    (2, 'La huerta de Pep', 'B716721', 'Girona'),
    (3, 'De la mar el mero', 'B2334231', 'Tarragona'),
    (4, 'La Huerta Paco', 'B11111', 'Segovia');

-- TABLA INTERMEDIA DE PRODUCTOS Y PROVEEDORES
CREATE TABLE producto_proveedor (
    idproducto INTEGER NOT NULL,
    idproveedor INTEGER NOT NULL,
    cantidad INTEGER NOT NULL,
    PRIMARY KEY (idproducto, idproveedor),
    CONSTRAINT fk_producto FOREIGN KEY (idproducto) REFERENCES productos(idproducto) ON UPDATE CASCADE,
    CONSTRAINT fk_proveedor FOREIGN KEY (idproveedor) REFERENCES proveedores(idproveedor) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- INSERTAR DATOS EN producto_proveedor
INSERT INTO producto_proveedor (idproducto, idproveedor)
VALUES (1, 1, 100), (1, 2, 180), (2, 1, 450), (3, 2, 120), (4, 3, 200);
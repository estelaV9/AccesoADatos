# Ejercicio Hibernate Gestión de Coches
## Introducción
Realizar una <a href="https://github.com/estelaV9/AccesoADatos/tree/master/Tema4_MapeoObjetoRelacional_Hibernate/PracticaHibernate_CRUDCoches/EstelaDeVega_CrudCochesHibernate">aplicación</a> para la **Gestión de Coches** usando **Hibernate**.

## Enunciado
Realizar aplicación CRUD de gestión de coches para trabajar con Hibernate. <br><br>
OBLIGATORIO: utilizar al menos un ComboBox y un TableView.<br><br>
En el TableView se deberá ver en todo momento los documentos que existan en la colección. Cuando se haga alguna modificación, borrado o inserción de documentos el TableView se deberá actualizar.<br><br>
Además, se deberá crear una clase Validar con todos sus métodos estáticos para validar algunos de los campos de tu formulario.<br><br>

<table>
  <tr>
    <th>
      El formulario para la práctica puede ser como el de la imagen del proyecto de <a href="https://github.com/estelaV9/AccesoADatos/tree/master/Tema3_AccesoBDNoSQL_MongoDB/PracticaMongoDB_GestionCoches/EstelaDeVega_EjercicioCrudGestionCoche">MongoDB</a>:
    </th>
    <th>
      Mi aplicación:
    </th>
  </tr>
  <tr>
    <td>
      <img src="https://github.com/estelaV9/AccesoADatos/blob/master/Tema3_AccesoBDNoSQL_MongoDB/PracticaMongoDB_GestionCoches/myProjectFiles/vistaFormularioEnunciado.png" width="300"> 
    </td>
    <td>
      <img src="https://github.com/estelaV9/AccesoADatos/blob/master/Tema3_AccesoBDNoSQL_MongoDB/PracticaMongoDB_GestionCoches/myProjectFiles/vistaGestionCoche.png" width="600">
    </td>
  </tr>
</table>


## Script SQL
```sql
DROP DATABASE IF EXISTS GestionCoches;
CREATE DATABASE GestionCoches;
USE GestionCoches;
CREATE TABLE coches (
  id integer NOT NULL AUTO_INCREMENT,
  matricula varchar(50) DEFAULT NULL,
  marca varchar(50) DEFAULT NULL,
  modelo varchar(50) DEFAULT NULL,
  tipo varchar(50) DEFAULT NULL,
  PRIMARY KEY (id)
)  ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4 AUTO_INCREMENT=1 ;

INSERT INTO coches (matricula, marca, modelo, tipo)
VALUES ('1234AAA', 'Audi', 'Q5', 'SUV'),
 ('2345BBB', 'Ford', 'S-mMax', 'SUV'),
 ('9876CCC', 'Renault', 'Clio', 'SUV');
```

---
>_IES Ribera de Castilla 24/25._

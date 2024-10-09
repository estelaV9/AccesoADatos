# TEMA 3: ACCESO A BASE DE DATOS NoSQL
## Introducción
Las bases de datos `NoSQL` (Not Only SQL) son sistemas de almacenamiento de información que no siguen el esquema relacional tradicional. 
En lugar de usar tablas relacionadas y SQL, las bases de datos NoSQL almacenan datos **sin una estructura fija** y no requieren 
un lenguaje estructurado como SQL para su manipulación.

### Características
- `Big Data`: Permiten manejar grandes volúmenes de datos no estructurados.
- `Almacenamiento en la nube`: Resuelven las limitaciones de las bases de datos relacionales.
- `Velocidad y rendimiento`: Priorizan la eficiencia y rapidez sobre la estructuración rígida de datos.

## MongoDB
### Características de MongoDB
MongoDB es una base de datos `documental`, lo que significa que la información se almacena en formato `BSON` (JSON binario), 
permitiendo una mayor flexibilidad en el almacenamiento y uso de diferentes tipos de datos. Los datos en MongoDB se agrupan en `colecciones`, 
pero los documentos dentro de una colección no tienen una estructura fija.


### Operaciones Básicas (CRUD)
#### Crear
- `insertOne()`: Inserta un único documento. Por ejemplo:
```js
db.albunes.insertOne({
    "nombre" : "Revenga",
    "autor" : "SOAD",
    "año" : 2005,
    "precio" : 20,
    "disco" : "Mezmerize"
})
```
No se incluye el campo `_id` porque MongoDB lo añade automaticamente. Se puede buscar el documento con `findOne()`.
```js
db.albunes.findOne()
```

- `insertMany()`: Inserta varios documentos al mismo tiempo. Recibe como argumento un array de documentos. Por ejemplo:
```js
db.albunes.insertMany([
  {
    "nombre" : "Toxicity",
    "autor" : "System of a Down",
    "año" : 2001
  },
  {
    "nombre" : "Steal This Album!",
    "autor" : "System of a Down",
    "año" : 2002
  },
  {
    "nombre" : "Mezmerize",
    "autor" : "System of a Down",
    "año" : 2005
  }
])
```


#### Leer
- `db.collection.find()`: Recupera documentos de una colección. Por ejemplo:
```js
db.albunes.findOne()
```
- `db.collection.find({});`: Buscar documentos que sigan unos determinados criterios. Por ejemplo:
 ```js
db.albunes.find({"año" : 1970});
```

#### Actualizar
- `db.collection.updateOne()`: Actualiza un documento que cumpla con un criterio. Por ejemplo:
  ```js
  db.albunes.updateOne(                  // colección
    { nombre: {$eq: "Revenga"}},          // criterio
    { $set: {autor: "System Of A Down"}}  // modificación
  )
  ```

#### Eliminar
- `db.collection.deleteOne()`: Elimina un documento. Por ejemplo:
  ```js
  db.albunes.deleteOne(      // coleccion
    {  nombre: "Revenga"}    // criterio
  )
  ```
- `db.collection.deleteMany()`: Elimina varios documentos. Por ejemplo:
  ```js
  db.albunes.deleteMany(      // coleccion
    { año: {$gte : 2005}}    // criterio
  )
  ```

### Búsquedas
MongoDB permite realizar búsquedas complejas utilizando el método `find()`, que se asemeja a las consultas `SELECT` en bases de datos SQL.<br>
Por ejemplo:
- **Devulve el primer documento que coincida con el campo y valores especificados**:
  ```js
  db.albunes.find({"nombre": {$eq: "Revenga"}})
  ```
- **Devulve todos los documentos que coincida con el campo y valores especificados**:
  ```js
  db.albunes.find({"año": {$ne:1970}});
  ```
- **Búsqueda sobre dos campos**:
  ```js
  db.albunes.find({ $or: [{"año": {$gt : 1965}}, {"autor": {$eq : "The Beatles"}}]})
  ```

### Operadores
#### Operadores de comparación
`eq`, `gt`, `gte`, `lt`, `lte`, `ne`, `in`, `nin`

#### Operadores lógicos
`or`, `and`, `not`


---
>_IES Ribera de Castilla 24/25._

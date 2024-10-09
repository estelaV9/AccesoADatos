# TEMA 1: ACCESO A BASE DE DATOS RELACIONALES
## Leer ficheros properties en Java
Un `archivo de propiedad` es un fichero con la extension **.properties** con sus contenido almacenado como una pareja clave=valor.
  ``` java
# Archivo properties
usuario=shrek
password=shrek
ruta=/home/shrek/resources
```

**1. Inicializar nuestro objeto y indicarle que cargue el fichero de propiedades.**
 ``` java
Properties propiedades = new Properties();
propiedades.load(new FileReader("config.properties"));
```

**2. Lectura de un valor en nuestro archivo properties**
 ``` java
propiedades.getProperty(miClave)
```

**3. Leer todas las propiedades**
Para leer todas las propiedades utilizaremos el objeto **Enumeration** que nos permitirá iterar sobre todas ellas.
Leeremos la clave de los valores en un bucle y obtendremos la clave. 
 ``` java
Enumeration<Object> claves = propiedades.keys(); // El objeto Enumeration almacena todas las claves que
                                                  // incluye nuestro objeto Properties
while (claves.hasMoreElements()) {
  Object clave = claves.nextElement();
  System.out.println(clave.toString() + " - " + propiedades.get(clave).toString());
} // SE RECORRE TODOS LOS ELEMENTOS Y LO IMPRIME 
```
Todo ello dentro de las excepciones `FileNotFoundException` y `IOException`.

> [!NOTE]
> <a href="https://github.com/estelaV9/AccesoADatos/tree/master/Tema1_AccesoBDRelacionales/FicheroPropiedades">EJERCICIO DE INICIO</a>
> 1 - Crear un objeto
> ```java
> Properties propiedades = new Properties();
> ```
> 2 - Indicarle que cargue el fichero de propiedades
> ```java
> properties.load(new FileInputStream(new File("src/main/resources/configuration/database.properties")));
> ```
> 3 - Lectura del valor en el archivo properties
> ```java
> host=String.valueOf(properties.get("host"));
> ```
> 4 - Leer todas las propiedades.
> 5 - Poner las excepciones `FileNotFoundException` y `IOException`.

## Acceso a Bases de Datos Relacionales 

### Ejercicio de Formulario de JavaFx con acceso a base de datos
<a href="https://github.com/estelaV9/AccesoADatos/blob/master/Tema1_AccesoBDRelacionales/EjercicioFormularioFX/PracticaFormulario.md">Enunciado</a> del ejercicio y manual.
Desarrollar una <a href="https://github.com/estelaV9/AccesoADatos/tree/master/Tema1_AccesoBDRelacionales/EjercicioFormularioFX/EstelaDeVega_EjercicioFormulario">aplicación</a> JavaFX con mínimo dos formularios y dos tablas en la base de datos.

Puntos clave de este ejercicio:
#### Configurar las columnas de una tabla al atributo de la clase 
1- En el initializable, referenciar las columnas al nombre de los atributos de la clase:
```java
nameProductCol.setCellValueFactory(new PropertyValueFactory<>("nameProduct"));
```
2- Crear el observableList con el array que contiene la lista de productos
```java
ObservableList<Product> listProduct =
      FXCollections.observableArrayList(ProductDAO.listProduct(ConnectionDB.con));
```
3- Añadir el observableList a la tabla
```java
CubeTable.setItems(listProduct); // ESTABLECER LISTA
```

#### Abrir una dirección en un navegador
```java
// METODO QUE REDIRIGE A MI PERFIL DE GITHUB
try {
    Desktop.getDesktop().browse(new URI("https://github.com/estelaV9"));
    /*SE USA LA CLASE DESKTOP QUE PERMITE HACER COSAS RELACIONADAS CON EL ESCRITORIO DEL ORDENADOR
    getDesktop() ES UN METODO QUE PROPORCIONA UNA INSTANCIA, ES DECIR, UN OBJETO DE LA CLASE DESKTOP.
    EL METODO browse() NOS PERMITE ABRIR UNA URL EN EL NAVEGADOR WEB PREDETERMINADO*/
} catch (Exception e) {
    e.printStackTrace();
}
```

#### Conexión
- `desconectar` la base de datos.
```java
public static void desconectar() throws SQLException {
    con.close();
} // METODO PARA DESCONECTAR UNA APP A LA DATABASE
```
- `conectar` la base de datos.
```java
public static Connection con;
  public static Connection conectar() throws ClassNotFoundException, SQLException {
      boolean connect = false;
      Properties properties = new Properties();
      String host = "", port = "", name = "", username = "", password = "";

      try {
          properties.load(new FileInputStream(new File("src/main/resources/Configuration/database.properties")));
          host = String.valueOf(properties.get("host"));
          port = String.valueOf(properties.get("port"));
          name = String.valueOf(properties.get("name"));
          username = String.valueOf(properties.get("username"));
          password = String.valueOf(properties.get("password"));
          Class.forName("com.mysql.cj.jdbc.Driver");

          con = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + name + "?serverTimezone=UTC",
                  username, password);
          return con;
      } catch (FileNotFoundException e) {
          // TODO Auto-generated catch block
          return null;
      } catch (IOException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
          return null;
      }
  } // METODO PARA CONECTAR UNA APP A LA DATABASE
```
  

---
>_IES Ribera de Castilla 24/25._

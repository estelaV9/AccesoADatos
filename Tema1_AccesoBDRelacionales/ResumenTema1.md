# TEMA 1: ACCESO A BASE DE DATOS RELACIONALES

## Métodos DAO
En la clase `ConnectionDB` donde se guardará los métodos de `conectar()` y `desconectar` añadiremos un atributo estático de tipo `Connection`.
```java
// ATRIBUTO ESTATICO DE TIPO CONEXION PARA PODER LLAMARLO DESDE CUALQUIER CLASE
public static Connection con;
```

### Insertar
Para el método insertar, utilizaremos un ejemplo de insertar usuario. Este método tiene como objetivo insertar un nuevo usuario.
1. **Declarar la sentencia SQL**: Definimos la sentencia SQL con una estructura preparada para evitar inyecciones SQL. En este caso, la sentencia tiene cuatro parámetros (`?`) que se llenarán con los valores del usuario.

2. **Crear el `PreparedStatement`**: Usamos `con.prepareStatement(sqlInsert)` para crear un `PreparedStatement` que nos permite asignar valores a los parámetros de la sentencia SQL.

3. **Asignar valores a los parámetros**: Llenamos cada parámetro de la sentencia (`?`) usando el objeto cubeUser. Esto se hace con los métodos `statement.setString`, donde se asignan el nombre de usuario, la contraseña, el correo electrónico y la fecha de registro.

4. **Ejecutar la inserción y devolver el resultado**: Ejecutamos la sentencia con `executeUpdate()`, que devuelve el número de filas afectadas. Si al menos una fila fue insertada, el método devuelve `true`, indicando que la operación fue exitosa; de lo contrario, devuelve `false`.

```java
public static boolean insertUser(Connection con, CubeUser cubeUser) {
    try {
        String sqlInsert = "INSERT INTO cube_users (NAME_USER, PASSWORD_USER, MAIL, REGISTRATION_DATE) " +
                "VALUES (?, ?, ?, ?);"; // DECLARAR LA SENTENCIA SQL

        PreparedStatement statement = con.prepareStatement(sqlInsert); // CREAR EL PreparedStatement

        // ASIGNAR LOS VALORES A LOS PARAMETROS
        statement.setString(1, cubeUser.getNameUser());
        statement.setString(2, cubeUser.getPasswordUser());
        statement.setString(3, cubeUser.getMail());
        statement.setString(4, String.valueOf(cubeUser.getRegistrationDate()));

        int rowsInserted = statement.executeUpdate(); // EJECUTAR INSERCION Y VERIFICAR EL RESULTADO
        if (rowsInserted > 0) {
            return true;
        } // SI SE HA INSERTADO, RETORNARA TRUE
    } catch (SQLException e) {
        StaticCode.Alerts("ERROR", "Error de conexión", "¡ERROR!",
                "Error al conectar a la base de datos: " + e.getMessage());
        return false;
    }
    return false;
} // METODO PARA INSERTAR USUARIO
```

### Modificar 
El método `modificar` permite actualizar los datos de un usuario existente en la tabla de la base de datos.
1. **Declarar la sentencia SQL**: Creamos la sentencia SQL para actualizar el nombre y el correo de un usuario en la tabla `CUBE_USERS`. Usamos `?` como marcadores de posición para asignar los valores dinámicamente.

2. **Crear el `PreparedStatement`**: Con `connection.prepareStatement(sqlUpdate)`, inicializamos el `PreparedStatement`, que nos permite llenar los valores para la sentencia SQL preparada.

3. **Asignar valores a los parámetros**: Utilizamos `statement.setString` para asignar el nuevo nombre y correo electrónico del usuario, y el correo actual (`mailUser`) que sirve como criterio de búsqueda en la tabla.

4. **Ejecutar la actualización y devolver el resultado**: Con `executeUpdate()`, se ejecuta la sentencia de actualización. Si una o más filas fueron actualizadas, el método devuelve `true`, indicando éxito.

```java
public static boolean modifyUser(Connection connection, String nameUser, String emailUser, String mailUser) {
    try {
        String sqlUpdate = "UPDATE CUBE_USERS SET NAME_USER = ?, MAIL = ? WHERE MAIL = ?";
        PreparedStatement statement = connection.prepareStatement(sqlUpdate);
        statement.setString(1, nameUser);
        statement.setString(2, emailUser);
        statement.setString(3, mailUser);
        int rowsUpdate = statement.executeUpdate();
        if (rowsUpdate > 0) {
            return true;
        } // SI SE HA ACTUALIZADO CORRECTAMENTE, RETORNARA TRUE
    } catch (SQLException e) {
        StaticCode.Alerts("ERROR", "Error de conexión", "¡ERROR!",
                "Error al conectar a la base de datos: " + e.getMessage());
        return false;
    }
    return false;
} // METODO PARA MODIFICAR AL USUARIO
```

### Eliminar 
El método `eliminar` elimina un usuario de la tabla de la base de datos.

1. **Declarar la sentencia SQL**: Definimos la sentencia SQL para eliminar un usuario. Usamos `?` para el marcador de posición del correo electrónico del usuario.

2. **Crear el `PreparedStatement`**: Inicializamos el `PreparedStatement` para ejecutar la sentencia de eliminación.

3. **Asignar valor al parámetro**: Usamos `statement.setString(1, mailUser)` para establecer el valor del correo del usuario que se quiere eliminar.

4. **Ejecutar la eliminación y devolver el resultado**: Usamos `executeUpdate()` para realizar la eliminación. Si al menos una fila es eliminada, el método devuelve `true`.

```java
public static boolean deleteUser(Connection con, String mailUser) {
    try {
        String sqlDelete = "DELETE FROM CUBE_USERS WHERE MAIL = ?";
        PreparedStatement statement = con.prepareStatement(sqlDelete);
        statement.setString(1, mailUser);
        int rowsDelete = statement.executeUpdate();
        if (rowsDelete > 0) {
            RegistrationCtrller.cubeUser = null; // INVALIDAR USUARIO
            return true;
        } // SI HA ELIMINADO CORRECTAMETNE RETURN TRUE
    } catch (SQLException e) {
        StaticCode.Alerts("ERROR", "Error de conexión", "¡ERROR!",
                "Error al conectar a la base de datos: " + e.getMessage());
        return false;
    }
    return false;
} // METODO PARA ELIMINAR USUARIO
```

> [!Note]
> Cuando una consulta devuelva resultados, se extraen con `Resulset`.
> ```java
> ResultSet resultSet = statementQuery.executeQuery();
> if (resultSet.next()) {
>    return resultSet.getString("NAME_USER");
> } // SI DEVUELVE DATOS EN LA CONSULTA, RETORNARA EL NOMBRE DEL USUARIO
> ```



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







---






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


### Conexión a la base de datos
Para conectar a la base de datos, podremos usar la `clase R` con su archivo `properties` para centralizar los datos de configuración, o hacerlo sin esta clase y definir directamente los datos en el código.

#### Conexión usando la Clase R
1. Crearemos un archivo llamado `database.properties`. Allí, colocaremos los datos de conexión de la base de datos.
```java
host=localhost
port=3306
name=mi_basedatos
username=mi_usuario
password=mi_contraseña
```

2. La `clase R` proporciona métodos para cargar recursos. Implementaremos métodos para obtener los datos desde el archivo `database.properties`:
```java
import java.io.File;
import java.io.InputStream;
import java.net.URL;

public class R {
    public static InputStream getImage(String name) {
        return Thread.currentThread().getContextClassLoader().getResourceAsStream("images" + File.separator + name);
    }
    public static InputStream getProperties(String name) {
        return Thread.currentThread().getContextClassLoader().getResourceAsStream("configuration" + File.separator + name);
    }
    public static URL getUI(String name) {
        return Thread.currentThread().getContextClassLoader().getResource("ui" + File.separator + name);
    }
}
```
3. Conexión a la base de datos
En la conexión usaremos `R.getProperties("database.properties")` para cargar los datos del archivo configuración. Esto nos permite cambiar la configuración sin modificar el código.
```java
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DatabaseConnection {
    public static Connection conectar() {
        try {
            Properties properties = new Properties();
            properties.load(R.getProperties("database.properties"));

            String host = properties.getProperty("host");
            String port = properties.getProperty("port");
            String name = properties.getProperty("name");
            String username = properties.getProperty("username");
            String password = properties.getProperty("password");

            return DriverManager.getConnection(
                "jdbc:mysql://" + host + ":" + port + "/" + name + "?serverTimezone=UTC", 
                username, 
                password
            );
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
```

#### Conexión sin la Clase R pero usando el archivo properties
En esta opción, configuramos la conexión a la base de datos utilizando un archivo de propiedades (`properties`) para almacenar los datos de conexión de forma externa, sin la ayuda de la clase `R`. Esto permite modificar las credenciales o detalles de conexión sin tener que cambiar el código directamente.

1. Creamos el archivo `database.properties` con los datos necesarios para la conexión.
2. Conectar a la Base de datos usando el archivo `properties`:
En lugar de la clase `R`, crearemos un objeto `Propertiess` para leer el archivo `database.properties`. De esta forma, el código carga los valores de conexión desde el archivo sin depender de la clase `R`.

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

#### Conexión sin la Clase R y sin el archivo properties
Si prefieres no usar la clase `R`, puedes definir directamente los datos de conexión en tu código. Esto hace que el código sea menos flexible, ya que los datos están codificados de forma rígida, pero también puede ser útil para pruebas rápidas.
```java
import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnectionDirect {
    public static Connection conectar() {
        String host = "localhost";
        String port = "3306";
        String name = "mi_basedatos";
        String username = "mi_usuario";
        String password = "mi_contraseña";

        try {
            return DriverManager.getConnection(
                "jdbc:mysql://" + host + ":" + port + "/" + name + "?serverTimezone=UTC", 
                username, 
                password
            );
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
```

### Desconectar la conexión
`Desconectar` la base de datos.
```java
public static void desconectar() throws SQLException {
    con.close();
} // METODO PARA DESCONECTAR UNA APP A LA DATABASE
```


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




---
>_IES Ribera de Castilla 24/25._

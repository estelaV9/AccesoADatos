# TEMA 2: FICHEROS JSON
## Jackson ObjectMapper
La clase `ObjectMapper` de la biblioteca **com.fasterxml.jackson.databind.ObjectMapper** es una forma sencilla de trabajar con JSON en Java. Permite:
- `Deserializar` JSON a objetos Java (convertir JSON en objetos Java).
- `Serializar` objetos Java a JSON (convertir objetos Java en JSON).

### Dependencias
Para usar Jackson en un proyecto Maven, se debe agregar la siguiente dependencia al archivo `pom.xml`:
```xml
<dependency>
    <groupId>com.fasterxml.jackson.core</groupId>
    <artifactId>jackson-databind</artifactId>
    <version>2.17.2</version>
</dependency>
```

## Serializar Objeto Java a JSON
Para convertir un objeto Java a JSON se usa el método `writeValue` de `ObjectMapper`. A continuación, un ejemplo de la clase **Car** y su serialización:

### Clase `Car.java`:
```java
public class Car {
    private String brand;
    private int doors;

    public String getBrand() { return this.brand; }
    public void setBrand(String brand) { this.brand = brand; }

    public int getDoors() { return this.doors; }
    public void setDoors(int doors) { this.doors = doors; }
}
```

### Clase `EjemploJackson.java`:
```java
public class EjemploJackson {
    public static void main(String[] args) {
        ObjectMapper objectMapper = new ObjectMapper();
        Car car = new Car();
        car.setBrand("Mercedes");
        car.setDoors(5);

        try {
            objectMapper.writeValue(new File("target/car.json"), car);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```
La salida en el archivo **car.json** sería:
```json
{"brand":"Mercedes", "doors":5}
```

## Serializar Lista de Objetos Java a JSON
Para serializar una lista de objetos Java, también usamos `writeValue`. Serializar una lista de autos:

### Clase `EjemploJackson.java`:
```java
public class EjemploJackson {
    public static void main(String[] args) {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Car> list = new ArrayList<Car>();

        Car car = new Car();
        car.setBrand("Mercedes");
        car.setDoors(5);
        list.add(car);

        car = new Car();
        car.setBrand("BMW");
        car.setDoors(4);
        list.add(car);

        try {
            String json = objectMapper.writeValueAsString(list);
            System.out.println(json);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```
El resultado sería:
```json
[{"brand":"Mercedes", "doors":5}, {"brand":"BMW", "doors":4}]
```

## Convertir JSON a Objeto Java
Para deserializar un JSON a un objeto Java, se utiliza el método `readValue` de `ObjectMapper`:
```java
public class EjemploJackson {
    public static void main(String[] args) {
        ObjectMapper objectMapper = new ObjectMapper();
        String json = "{"brand":"Mercedes", "doors":5}";

        try {
            Car car = objectMapper.readValue(json, Car.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```

### URL JSON
```java
ObjectMapper objectMapper = new ObjectMapper();
try {
    URL url = new URL("file:data/car.json");
    Car car = objectMapper.readValue(url, Car.class);
}catch (Exception e) {
    e.printStackTrace();
}
```

### Clase InputStream
```java
ObjectMapper objectMapper = new ObjectMapper();
try {
    InputStream input = new FileInputStream("data/car.json");
    Car car = objectMapper.readValue(input, Car.class);
}catch (Exception e) {
    e.printStackTrace();
}
```

## Convertir JSON a una Lista o Array
Jackson también permite convertir JSON en listas o arrays de objetos Java:
```java
public class EjemploJackson {
    public static void main(String[] args) {
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonArray = "[{"brand":"ford"}, {"brand":"Fiat"}]";

        try {
            Car[] cars = objectMapper.readValue(jsonArray, Car[].class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```


## Permitir Valores Nulos en Campos Primitivos
Si el JSON contiene campos con valores nulos para tipos primitivos, también puedes configurarlo:
```java
ObjectMapper objectMapper = new ObjectMapper();
objectMapper.configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, false);

String json = "{"brand":"Mercedes", "doors":null}";

try {
    Car car = objectMapper.readValue(json, Car.class);
} catch (Exception e) {
    e.printStackTrace();
}
```


## Tarea leer ficheros
Puntos **clave** respecto al <a href="https://github.com/estelaV9/AccesoADatos/blob/master/Tema2_FicherosJSON/PracticaLeerJSON/enunciadoJson.md">ejercicio</a> de leer ficheros json:
### Crear un objeto de ObjectMapper para manejar la conversión de JSON a objetos
```java
    public static final ObjectMapper JSON_MAPPER = new ObjectMapper();
```

### Mostrar en un ListView cuando se pulsa un botón
>[!NOTE]
> 1 - `leer` un archivo JSON y `convertirlo` en una lista de objetos:
> ```java
> ArrayList<Object> nameArray  =
>      JSON_MAPPER.readValue(new File("src/main/resources/json/fichero.json"),
>             JSON_MAPPER.getTypeFactory().constructCollectionType
>                     (ArrayList.class, Object.class));
> ```
> 2 - Crear el `observableList` de tipo del Objeto con la lista de objetos:
> ```java
> ObservableList<Pelicula> items = FXCollections.observableArrayList(peliculas);
> ```
> 3 - Añadir el observableList al `ListView`
> ```java
>  listView.setItems(items);
> ```


```java
  void onImportarAction(ActionEvent event) {
      try {
          // CONVERTIR EL FICHERO JSON A ARRAYLIST
          /** lee el archivo JSON y lo convierte a una lista de objetos Pelicula **/
          ArrayList<Pelicula> peliculas =
                  JSON_MAPPER.readValue(new File("src/main/resources/json/peliculas.json"),
                          JSON_MAPPER.getTypeFactory().constructCollectionType
                                  (ArrayList.class, Pelicula.class));

          // SE CREA UN OBSERVABLE LIST DE TIPO PELICULAS Y SE AÑADE EL ARRAYLIST DE PELICULAS
          ObservableList<Pelicula> items = FXCollections.observableArrayList(peliculas);
          listView.setItems(items); // SE AÑADE LOS ITEMS A LISTVIEW

      } catch (Exception ex) {
          // MOSTRAR ALERTA EN CASO DE ERROR
          StaticCode.Alerts("ERROR", "Error al importar", "ERROR",
                  "Error: " + ex.getMessage());
      }
  } // MOSTRAR LAS PELICULAS EN LA LISTA CUANDO SE PULSA EL BOTON DE IMPORTAR
```

### Settear los datos en los TextFields cuando se SELECCIONA un objeto
> [!NOTE]
> 1 - `Guardar` el objeto seleccionado en un objeto
> ```java
> Pelicula pelicula = listView.getSelectionModel().getSelectedItem();
> ```
> 2 - Verificar si es `nulo` el objeto creado

```java
void onMostrarPeliculasClick(MouseEvent event) {
    Pelicula pelicula = listView.getSelectionModel().getSelectedItem(); // SE GUARDA EL OBJETO PELICULA SELECCIONADO
    if (pelicula == null) {
        // MOSTRAR ALERTA EN CASO DE QUE SE SELECCIONE UNA FILA VACIA
        StaticCode.Alerts("ERROR", "Pelicula nula", "ERROR",
                "No has seleccionado ninguna pelicula.\nPor favor, elija una pelicula para ver sus datos.");
    } else {
        // SE SETTEAN LOS DATOS EN LOS TEXTFIELDS
        titleTxt.setText(pelicula.getTitulo());
        dateTxt.setText(pelicula.getFecha());
        directorTxt.setText(pelicula.getDirector());
        genderTxt.setText(pelicula.getGenero());
    }
} // SETTEAR LOS DATOS EN LOS TEXTFIELD CUANDO SE SELECCIONA UNA PELICULA
```



---
>_IES Ribera de Castilla 24/25._

# Tema 7 - Cómo crear una API REST usando Spring Boot, Maven y MySQL
Uno de los beneficios de las API REST es la flexibilidad y escalabilidad que brinda a nuestra aplicación.  
Dado que el cliente y el servidor son independientes, el protocolo REST separa el almacenamiento de datos de la interfaz de usuario en el servidor.

---

## Inicialización y configuración del proyecto
Hay diferentes formas de configurar una nueva aplicación Spring Boot. Usaremos **Spring Initializer**.

### Método 1: Configuración usando IntelliJ
1. Abre **IntelliJ IDEA** y crea un nuevo proyecto:  
   `File > New > Project`
2. Selecciona **Spring Initializer**.
3. Ingresa las siguientes propiedades del proyecto:
   - **Type**: Maven
   - **Language**: Java
   - **Group**: `com.juan`
   - **Artifact**: `employee_app`
   - **Java SDK**: 11+
   - **Spring Boot**: 3.0.1
   - **Packaging**: JAR
4. Agrega las siguientes dependencias:
   - Spring Web
   - Spring Data JPA
   - MySQL Driver
5. Haz clic en **Finish**.

---

### Método 2: Configuración usando Spring Initializer (web)
Si no usas IntelliJ, puedes ir a [Spring Initializr](https://start.spring.io/), ingresar las propiedades del proyecto,  
descargar el ZIP, descomprimirlo y abrirlo en tu editor de código favorito.

---

## 📄 Archivo `pom.xml`
El `pom.xml` define las dependencias del proyecto:

```xml
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.5.2</version>
        <relativePath/>
    </parent>
    <groupId>com.juan</groupId>
    <artifactId>employee_app</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <name>employee_app</name>
    <description>Demo project for Spring Boot</description>
    <properties>
        <java.version>11</java.version>
    </properties>
    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <scope>runtime</scope>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>
    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>
</project>
```

## Configuración de MySQL, JPA e Hibernate
En el archivo `application.properties`, configura las siguientes propiedades:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/employee_db?useSSL=false&serverTimezone=UTC&useLegacyDatetimeCode=false
spring.datasource.username=root
spring.datasource.password=mypassword

server.port=8081

spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL5InnoDBDialect
spring.jpa.hibernate.ddl-auto=update
```

## Punto de entrada de la aplicación
La clase principal de la aplicación es EmployeeAppApplication.java:
```java
package com.juan.employee_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EmployeeAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmployeeAppApplication.class, args);
    }

}
```

## Uso de @Controller, @Service y @Repository en Spring Boot
### @Controller
Maneja las solicitudes HTTP y envía respuestas.
```java
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public User getUserById(@PathVariable int id) {
        return userService.getUserById(id);
    }

    @PostMapping
    public void createUser(@RequestBody User user) {
        userService.createUser(user);
    }
}
```

### @Service
Contiene la lógica de negocio.
```java
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User getUserById(int id) {
        return userRepository.findById(id).orElse(null);
    }

    public void createUser(User user) {
        userRepository.save(user);
    }
}
```

### @Repository
Accede a la base de datos y realiza operaciones CRUD.
```java
@Repository
public interface UserRepository extends JpaRepository<User, Integer> { }
```


## Ejecutar la aplicación
Usa Postman para probar la API RESTful con métodos HTTP (`POST`, `GET`, `PUT`, `DELETE`).

### Ejemplo de solicitudes:
1. Obtener todos los empleados:
    - URL: `http://localhost:8081/api/users`
    - Método: `GET`

2. Crear un nuevo empleado:
   - URL: `http://localhost:8081/api/users`
   - Método: `POST`
   - Body (JSON):
      ```json
      {
        "name": "Juan",
        "email": "juan@example.com"
      }
      ```

3. Actualizar un empleado:
   - URL: `http://localhost:8081/api/users/{id}`
   - Método: `PUT`
   - Body (JSON):
      ```json
      {
        "name": "Juan Updated",
        "email": "juan.updated@example.com"
      }
      ```

4. Eliminar un empleado:
   - URL: `http://localhost:8081/api/users/{id}`
   - Método: `DELETE`

## Ejercicio Obligatorio
Crea una <a href="https://github.com/estelaV9/AccesoADatos/tree/master/Tema5_APIyJWT/Practica_APIyOpenAPI_Swagger/EstelaDeVega_PracticaAPI">API</a> que ofrezca servicios web de búsqueda de hoteles. La base de datos debe contener:
- **Hoteles**: nombre, descripción, categoría, ¿piscina?, localidad.
- **Habitaciones**: tamaño, 1 ó 2 personas, precio/noche, ¿incluye desayuno?, ¿ocupada?.

Operaciones requeridas:
- Búsqueda de hotel por localidad o categoría.
- Búsqueda de habitaciones de un hotel por tamaño y precio (rango mínimo → máximo). Solo mostrará habitaciones libres.
- Registrar un nuevo hotel.
- Registrar una nueva habitación a un hotel.
- Eliminar una habitación determinada de un hotel.
- Modificar una habitación para indicar que está ocupada.


## Documentación con Swagger
Una vez documentado el proyecto, se generarán los siguientes endpoints:
- `http://localhost:8081/v3/api-docs`: Documentación en formato JSON.
- `http://localhost:8081/v3/api-docs.yaml`: Documentación en formato YAML.
- `http://localhost:8081/swagger-ui.html`: Portal web para probar la API.



---

# Creación de APIs REST con Swagger y Spring Boot
OpenAPI es un estándar para la descripción de las interfaces de programación, o **application programming interfaces (API)**. La especificación OpenAPI define un formato de descripción abierto e independiente de los fabricantes para los servicios de API. En particular, OpenAPI puede utilizarse para describir, desarrollar, probar y documentar las API compatibles con REST.

La actual especificación OpenAPI surgió del proyecto predecesor **Swagger**. La empresa de desarrollo SmartBear sometió la especificación existente de Swagger a una licencia abierta y dejó el mantenimiento y desarrollo posterior en manos de la iniciativa OpenAPI.

Para ello, comenzaremos añadiendo algunas dependencias a nuestro proyecto, que nos permitirán documentar nuestra aplicación y cada uno de los endpoints, y generar un portal desde donde podremos publicarla como API.

```xml
<dependency>
    <groupId>org.springdoc</groupId>
    <artifactId>springdoc-openapi-ui</artifactId>
    <version>1.5.2</version>
</dependency>
```

## Configuración de la API
Implementaremos una clase de configuración donde definiremos algunas propiedades generales para toda la aplicación.
```java
@Configuration
public class ShopConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components())
                .info(new Info().title("MyShop API")
                        .description("Ejemplo de API REST")
                        .contact(new Contact()
                                .name("Juan")
                                .email("juan@gamail.com")
                                .url("https://juan.com"))
                        .version("1.0"));
    }
}
```

## Documentación de Controladores y Endpoints
Para cada uno de los controladores, definiremos toda la documentación tanto para el propio controlador como para cada uno de los endpoints que se expongan.

### Anotaciones utilizadas:
- **@Tag**: Permite documentar el controlador.
- **@Operation**: Permite definir una descripción para la operación.
- **@ApiResponses**: Permite documentar la forma en que una operación concreta responde, teniendo en cuenta las posibles respuestas en caso de error.
```java
/** Controlador para productos */
@RestController
@Tag(name = "Products", description = "Catálogo de productos")
public class ProductController {

    private final Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductService productService;

    @Operation(summary = "Obtiene el listado de productos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listado de productos",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Product.class)))),
    })
    @GetMapping(value = "/products", produces = "application/json")
    public ResponseEntity<Set<Product>> getProducts(@RequestParam(value = "category", defaultValue = "") String category) {
        logger.info("inicio getProducts");
        Set<Product> products = null;
        if (category.equals(""))
            products = productService.findAll();
        else
            products = productService.findByCategory(category);

        logger.info("fin getProducts");
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @Operation(summary = "Obtiene un producto determinado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Existe el producto", content = @Content(schema = @Schema(implementation = Product.class))),
            @ApiResponse(responseCode = "404", description = "El producto no existe", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @GetMapping(value = "/products/{id}", produces = "application/json")
    public ResponseEntity<Product> getProduct(@PathVariable long id) {
        Product product = productService.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));

        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @Operation(summary = "Registra un nuevo producto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se registra el producto", content = @Content(schema = @Schema(implementation = Product.class)))
    })
    @PostMapping(value = "/products", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        Product addedProduct = productService.addProduct(product);
        return new ResponseEntity<>(addedProduct, HttpStatus.OK);
    }

    @Operation(summary = "Modifica un producto en el catálogo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se modifica el producto", content = @Content(schema = @Schema(implementation = Product.class))),
            @ApiResponse(responseCode = "404", description = "El producto no existe", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @PutMapping(value = "/products/{id}", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Product> modifyProduct(@PathVariable long id, @RequestBody Product newProduct) {
        Product product = productService.modifyProduct(id, newProduct);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @Operation(summary = "Elimina un producto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se elimina el producto", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "El producto no existe", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @DeleteMapping(value = "/products/{id}", produces = "application/json")
    public ResponseEntity<Response> deleteProduct(@PathVariable long id) {
        productService.deleteProduct(id);
        return new ResponseEntity<>(Response.noErrorResponse(), HttpStatus.OK);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Response> handleException(ProductNotFoundException pnfe) {
        Response response = Response.errorResonse(NOT_FOUND, pnfe.getMessage());
        logger.error(pnfe.getMessage(), pnfe);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
```

## Documentación del Modelo de Datos
Además, podremos añadir algunas anotaciones a las clases de nuestro modelo de datos para ampliar la documentación de nuestra nueva API.

### Anotaciones utilizadas:
- **@Schema**: Documenta un atributo, considerado como un campo de entrada (o salida).
- **@NotBlank**: Documenta que el atributo es obligatorio.
- **@Min**: Documenta el valor mínimo del atributo.

```java
/** Un producto del catálogo */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "products")
public class Product {

    @Schema(description = "Identificador del producto", example = "1", required = true)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Schema(description = "Nombre del producto", example = "Donuts", required = true)
    @NotBlank
    @Column
    private String name;

    @Schema(description = "Descripción del producto", example = "El mejor producto")
    @Column
    private String description;

    @Schema(description = "Nombre del producto", example = "Alimentación", required = true)
    @NotBlank
    @Column
    private String category;

    @Schema(description = "Precio del producto", example = "3.50", defaultValue = "0.00")
    @Column
    @Min(value = 0)
    private float price;

    @Schema(description = "Fecha de registro del producto", example = "2021-03-01")
    @Column(name = "creation_date")
    private LocalDateTime creationDate;
}
```

## Endpoints Generados
Una vez que documentemos el proyecto como API, lo lanzaremos. Las librerías de SpringDoc incluidas, unidas a la documentación que hemos añadido a las clases del proyecto, hacen que se generen tres nuevos endpoints:

1. `http://localhost:8081/v3/api-docs`: Contiene la documentación de toda la API en formato JSON.
2. `http://localhost:8081/v3/api-docs.yaml`: Contiene la documentación de toda la API en formato YAML, siguiendo la especificación OpenAPI 3.
3. `http://localhost:8081/swagger-ui.html`: Contiene un portal web con toda la documentación, incluyendo además todo lo necesario para que se puedan realizar pruebas.


---
>_Estela de Vega Martín | IES Ribera de Castilla 24/25_

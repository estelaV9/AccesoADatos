# Hashing SHA-256 y SHA3-256 en Java
## Descripción
El **algoritmo de hash seguro** (`SHA`) es una de las funciones hash criptográficas más utilizadas. Un `hash criptográfico` se emplea para generar una firma digital de un texto o archivo de datos. 

El `algoritmo SHA-256` produce un hash de 256 bits (32 bytes) de longitud fija y casi único. 
Se trata de una **función unidireccional**, lo que significa que el valor original no puede ser recuperado a partir del hash generado. Actualmente, el algoritmo `SHA-2`, que incluye `SHA-256`, es uno de los más utilizados debido a su alta seguridad en el ámbito criptográfico.

Por su parte, `SHA-3` es el estándar más reciente en cuanto a algoritmos de hash seguro, sucediendo a `SHA-2`. En comparación con `SHA-2`, `SHA-3` emplea un enfoque diferente para generar un hash único y unidireccional, y en algunas implementaciones de hardware, puede ser considerablemente más rápido. Al igual que `SHA-256`, `SHA3-256` es la versión de longitud fija de 256 bits de `SHA-3`.

Como su nombre indica, `SHA-256v` produce un hash de 256 bits. Dado que el hash se presenta en formato hexadecimal, cada carácter codifica 4 bits (en lugar de los 8 bits habituales en ASCII), lo que implica que los 256 bits de un hash `SHA-256` se representan como 64 caracteres hexadecimales. Por lo tanto, es necesario usar un campo `varchar(64)` para almacenarlo.

## Dependencia
Para utilizarlo añadiremos la dependencia `Apache Commons Codec` para trabajar con `SHA-256`.
```xml
<dependency>
    <groupId>commons-codec</groupId>
    <artifactId>commons-codec</artifactId>
    <version>1.16.0</version>
</dependency>
```

Con esta dependencia, podremos usar la clase `DigestUtils`, que facilita la implementación del hash `SHA-256`
```java
String sha256hex = DigestUtils.sha256Hex(originalString);
```

## Ejemplo
```java
String contraseña = "ana";

String sha256hex1 = DigestUtils.sha256Hex(contraseña);
System.out.println(sha256hex1);
Usuario usuario1 = new Usuario("luis3@gmail.com", sha256hex1);
if (UsuariosDAO.guardarUsuario(con, usuario1))
    System.out.println("Usuario INSERTADO CORRECTAMENTE");

existeUsuario = UsuariosDAO.existeUsuario(con, "ana@gmail.com");
if (existeUsuario)
    System.out.println("El usuario EXISTE en la BD");

//String sha512hex=DigestUtils.sha3_512Hex(originalString);
```

---
>_IES Ribera de Castilla 24/25._

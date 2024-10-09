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



---
>_IES Ribera de Castilla 24/25._

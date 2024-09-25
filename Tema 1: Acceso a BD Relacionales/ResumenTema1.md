# TEMA 1: ACCESO A BASE DE DATOS RELACIONALES
## Leer ficheros properties en Java
> [!NOTE]
> EJERCICIO DE INICIO

Un **archivo de propiedad** es un fichero con la extension **.properties** con sus contenido almacenado como una pareja clave=valor.
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




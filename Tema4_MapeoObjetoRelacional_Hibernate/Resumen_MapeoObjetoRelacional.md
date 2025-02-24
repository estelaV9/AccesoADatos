# Tema 4: Mapeo Objeto-Relacional (Hibernate)
## Desfase Objeto-Relacional
El **desfase objeto-relacional** surge cuando en el desarrollo de una aplicación con un lenguaje orientado a objetos se hace uso de una base de datos relacional. Esta situación es común porque tanto los lenguajes orientados a objetos como las bases de datos relacionales están ampliamente extendidos.

### Ejemplo en Java
En nuestra aplicación Java, tendremos una clase con sus atributos y métodos:
```java
public class Personaje {
    private int id;
    private String nombre;
    private String descripcion;
    private int vida;
    private int ataque;

    public Personaje(. . .) {
      . . .
    }

    // getters y setters
}
```

Mientras que en la base de datos tendremos una tabla cuyos campos deben corresponder con los atributos de la clase:
```sql
CREATE TABLE personajes (
  id INT PRIMARY KEY AUTO_INCREMENT,
  nombre VARCHAR(50) NOT NULL,
  descripcion VARCHAR(50),
  vida INT DEFAULT 10,
  ataque INT DEFAULT 10
);
```

Esto implica que debemos hacer el mapeo manualmente, descomponiendo y recomponiendo objetos cada vez que interactuamos con la base de datos.

---

## ¿Qué es el Mapeo Objeto-Relacional (ORM)?
El **mapeo objeto-relacional** (ORM) es una técnica que permite mapear objetos de un lenguaje orientado a objetos (como Java) a tablas de una base de datos relacional. Esto elimina la necesidad de escribir manualmente consultas SQL para cada operación.

### Ejemplo con JDBC
Sin ORM, tendríamos que descomponer el objeto para construir una sentencia SQL:
```java
String sentenciaSql = "INSERT INTO personajes (nombre, descripcion, vida, ataque) VALUES (?, ?, ?, ?)";
PreparedStatement sentencia = conexion.prepareStatement(sentenciaSql);
sentencia.setString(1, personaje.getNombre());
sentencia.setString(2, personaje.getDescripcion());
sentencia.setInt(3, personaje.getVida());
sentencia.setInt(4, personaje.getAtaque());
sentencia.executeUpdate();

if (sentencia != null)
  sentencia.close();
```

### Ejemplo con Hibernate
Con Hibernate, el mapeo se simplifica usando anotaciones:
```java
@Entity
@Table(name="personajes")
public class Personaje {
  @Id // Marca el campo como la clave de la tabla
  @GeneratedValue(strategy = IDENTITY)
  @Column(name="id")
  private int id;
  @Column(name="nombre")
  private String nombre;
  @Column(name="descripcion")
  private String descripcion;
  @Column(name="vida")
  private int vida;
  @Column(name="ataque")
  private int ataque;

  public Personaje(. . .) {
    . . .
  }

  // getters y setters
}
```

Y la operación de guardado se reduce a:
```java
sesion = HibernateUtil.getCurrentSession();
sesion.beginTransaction();
sesion.save(personaje);
sesion.getTransaction().commit();
sesion.close();
```


## Hibernate
Hibernate es una herramienta de mapeo objeto-relacional (ORM) para Java que facilita el mapeo de atributos en una base de datos tradicional y el modelo de objetos de una aplicación.

### Configuración de Hibernate
El archivo de configuración `hibernate.cfg.xml` debe crearse en el paquete `resources`. Este archivo define la conexión a la base de datos.
```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE hibernate-configuration PUBLIC
        "-//Hibernate/Hibernate Configuration DTD 3.0//EN"
        "http://www.hibernate.org/dtd/hibernate-configuration-3.0.dtd">
<hibernate-configuration>
    <session-factory>
        <property name="connection.driver_class">com.mysql.jdbc.Driver</property>
        <property name="connection.url">jdbc:mysql://localhost/tutorial</property>
        <property name="connection.username">root</property>
        <property name="connection.password">toor</property>
        <property name="dialect">org.hibernate.dialect.MySQL5Dialect</property>
        <property name="hibernate.show_sql">true</property>
    </session-factory>
</hibernate-configuration>
```

### Clase `HibernateUtil`
Esta clase gestiona las sesiones de Hibernate:
```java
public class HibernateUtil {
    private static SessionFactory sessionFactory;
    private static Session session;

    /**
     * Crea la factoria de sesiones
     */
    public static void buildSessionFactory() {
      Configuration configuration = new Configuration();
      configuration.configure();
      // Se registran las clases que hay que mapear con cada tabla de la base de datos
      configuration.addAnnotatedClass(Clase1.class);
      configuration.addAnnotatedClass(Clase2.class);
      configuration.addAnnotatedClass(Clase3.class);
      . . .

      ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(
        configuration.getProperties()).build();
      sessionFactory = configuration.buildSessionFactory(serviceRegistry);
    }

    /**
     * Abre una nueva sesión
     */
    public static void openSession() {
      session = sessionFactory.openSession();
    }

    /**
     * Devuelve la sesión actual
     */
    public static Session getCurrentSession() {
      if ((session == null) || (!session.isOpen()))
        openSession();
      return session;
    }

    /**
     * Cierra Hibernate
     */
    public static void closeSessionFactory() {
      if (session != null)
        session.close();
      if (sessionFactory != null)
        sessionFactory.close();
    }
}
```

## Mapeo de Entidades/Relaciones con Clases/Atributos Java
### Anotaciones para Clases
- **@Entity**: Indica que la clase es una tabla en la base de datos.
- **@Table(name = “nombre_tabla”)**: Indica el nombre de la tabla.

### Anotaciones para Atributos
- **@Id**: Indica que un atributo es la clave.
- **@GeneratedValue(strategy = GenerationType.IDENTITY)**: Indica que es un valor autonumérico.
- **@Column(name = “nombre_columna”)**: Indica el nombre de la columna en la tabla.

### Ejemplo de Mapeo
```java
@Entity
@Table(name = "actor", catalog = "db_peliculas")
public class Actor {

  private Integer id;
  private String nombre;
  private Date fechaNacimiento;

  // Constructor/es
  public Actor() { . . . }

  @Id
  @GeneratedValue(strategy = IDENTITY)
  @Column(name = "id")
  public Integer getId() {
    return this.id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  @Column(name = "nombre")
  public String getNombre() {
    return this.nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  @Column(name = "fecha_nacimiento")
  public Date getFechaNacimiento() {
    return this.fechaNacimiento;
  }

  public void setFechaNacimiento(Date fechaNacimiento) {
    this.fechaNacimiento = fechaNacimiento;
  }

  @Override
  public String toString() {
    return nombre;
  }
}
```


## Relaciones en Hibernate
### Relaciones 1-1
```java
@Entity
@Table(name = "personajes")
public class Personaje {
  @OneToOne(cascade = CascadeType.ALL)
  @PrimaryKeyJoinColumn
  public Arma getArma() { return arma; }
}

@Entity
@Table(name = "armas")
public class Arma {
  @OneToOne(cascade = CascadeType.ALL)
  @PrimaryKeyJoinColumn
  public Personaje getPersonaje() { return personaje; }
}
```

### Relaciones 1-N
```java
@Entity
@Table(name = "personajes")
public class Personaje {
  @ManyToOne
  @JoinColumn(name="id_arma")
  public Arma getArma() { return arma; }
}

@Entity
@Table(name = "armas")
public class Arma {
  @OneToMany(mappedBy = "arma", cascade = CascadeType.ALL)
  public List<Personaje> getPersonajes() { return personajes; }
}
```

### Relaciones N-M
```java
@Entity
@Table(name = "enemigos")
public class Enemigo {
  @ManyToMany(cascade = CascadeType.DETACH)
  @JoinTable(name="enemigo_arma", 
    joinColumns={@JoinColumn(name="id_enemigo")}, 
    inverseJoinColumns={@JoinColumn(name="id_arma")})
  public List<Arma> getArmas() { return armas; }
}

@Entity
@Table(name = "armas")
public class Arma {
  @ManyToMany(cascade = CascadeType.DETACH, mappedBy = "armas")
  public List<Enemigo> getEnemigos() { return enemigos; }
}
```

## Operaciones sobre la Base de Datos
### Registrar un Objeto
```java
Session sesion = HibernateUtil.getCurrentSession();
sesion.beginTransaction();
sesion.save(unObjeto);
sesion.getTransaction().commit();
sesion.close();
```

### Modificar un Objeto
```java
Session sesion = HibernateUtil.getCurrentSession();
sesion.beginTransaction();
sesion.save(unObjeto);
sesion.getTransaction().commit();
sesion.close();
```

### Eliminar un Objeto
```java
Session sesion = HibernateUtil.getCurrentSession();
sesion.beginTransaction();
sesion.delete(unObjeto);
sesion.getTransaction().commit();
sesion.close();
```

### Búsquedas
#### Obtener un objeto por ID
```java
int id = . . .;
Cliente cliente = HibernateUtil.getCurrentSession().get(Cliente.class, id);
```

#### Obtener todos los objetos de una clase
```java
Query query = HibernateUtil.getCurrentSession().createQuery("FROM Cliente");
ArrayList<Cliente> clientes = (ArrayList<Cliente>) query.list();
```

#### Búsquedas con criterios
```java
String nombre = . . .;
Query query = HibernateUtil.getCurrentSession().
  createQuery("FROM Cliente c WHERE c.nombre = :nombre");
query.setParameter("nombre", nombre);
Cliente cliente = (Cliente) query.uniqueResult();
```

#### Consultas SQL directas
```java
SQLQuery sqlQuery = HibernateUtil.getCurrentSession().
  createSQLQuery("SELECT nombre, apellidos FROM clientes WHERE ciudad = :ciudad");
query.setParameter("ciudad", ciudad);
List resultado = query.list();
```


---
>_Estela de Vega Martín | IES Ribera de Castilla 24/25_

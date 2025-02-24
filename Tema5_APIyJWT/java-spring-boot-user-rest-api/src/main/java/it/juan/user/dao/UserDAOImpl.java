package it.juan.user.dao;

import javax.persistence.EntityManager;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import it.juan.user.entity.User;

@Repository
public class UserDAOImpl implements UserDAO {
/*
    El entity manager tiene dos responsabilidad fundamentales:
        • Define una conexión transaccional con la base de datos que debemos abrir y mantener
            abierta mientras estamos realizado operaciones. En este sentido realiza funciones
            similares a las de una conexión JDBC.
        • Además, mantiene en memoria una caché con las entidades que gestiona y es
            responsable de sincronizarlas correctamente con la base de datos cuando se realiza un
            flush. El conjunto de entidades que gestiona un entity manager se denomina su
            contexto de persistencia.

     */

    /*
    JPA EntityManager es parte del estándar de la API de persistencia de Java.
    Sin embargo, Hibernate Session proporciona muchas características que van más allá de la especificación JPA
     */


    /*
    HQL y JPQL
El lenguaje de consulta de Hibernate (HQL) y el lenguaje de consulta de persistencia de Java (JPQL)
 son lenguajes de consulta centrados en el modelo de objetos de naturaleza similar a SQL.
 JPQL es un subconjunto de HQL muy inspirado.
 Una consulta JPQL siempre es una consulta HQL válida, sin embargo, lo contrario no es cierto.
     */

    @Autowired
    private EntityManager entityManager;

    //SessionFactory factory = HibernateUtil.getSessionFactory();

    /*
    EntityManagerFactory es la implementación estándar, es la misma en todas las implementaciones.
     Si migra su ORM a cualquier otro proveedor como EclipseLink, TopLink,....,
     NO habrá ningún cambio en el enfoque para manejar la transacción.
     Por el contrario, si utiliza la fábrica de sesiones de hibernación,
     está vinculada a las API de hibernación y no puede migrar a un nuevo proveedor.

     La interfaz de EntityManager es similar a sessionFactory en hibernación.

    EntityManagerFactory es específico de JPA y sessionFactory son específicos de hibernación.

     */
    @Override
    public List<User> findAll() {
        /*
         Así de fácil es obtener la clase de implementación subyacente de Hibernate
          cuando se trabaja con JPA EntityManager con unwrap
         */

        Session currentSession = entityManager.unwrap(Session.class);
/*
        Si no quisieramos utilizar hibernate y usar JPA EntityManager por tanto la anterior linea nos sobraria
        EntityManager tiene una forma de hacer consultas
            EntityManager.createQuery(String, Class)
        es parte de JPA al igual que TypedQuery.getResultList() .
        Por tanto la llamada a EntityManager.unwrap() se puede eliminar y reemplazar con entityManager
         */

        //Query<User> theQuery = currentSession.createQuery("from User", User.class);
        Query<User> theQuery = currentSession.createQuery("SELECT u from User u", User.class);

        List<User> users = theQuery.getResultList();

        return users;

    }

    @Override
    public User findById(int id) {
        Session currentSession = entityManager.unwrap(Session.class);

        User user = currentSession.get(User.class, id);

        return user;
    }

    @Override
    public void save(User user) {
        Session currentSession = entityManager.unwrap(Session.class);
        Transaction t = currentSession.beginTransaction();
        /*
saveOrUpdate () , como su nombre lo indica, funciona como save() o update() en función del campo ID presente en la entidad o no. En la mayoría de los casos, es el método preferido para save() .

    Si el ID no está presente, se llama a save() .
    Si el ID está presente, se llama a update() .

 */

        currentSession.saveOrUpdate(user);
        t.commit();
        currentSession.close();

    }

    @Override
    public void deleteById(int id) {
        Session currentSession = entityManager.unwrap(Session.class);
        Transaction t = currentSession.beginTransaction();
        //forma facil
        //User user=findById(id);
        //currentSession.delete(user);
        //otra forma utilizando sentencias HQL DE hibernate

        Query theQuery = currentSession.createQuery("delete from User u where id IN (:idUser)");

        theQuery.setParameter("idUser", id);
        theQuery.executeUpdate();
        t.commit();
        currentSession.close();

    }
}
package jsl.com.farming.repository;

import jsl.com.farming.entities.Agricultor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AgricultorRepository extends JpaRepository<Agricultor, Integer> {
/*
En Spring Data JPA, se puede usar la anotación @Query para escribir consultas JPQL personalizadas.

El uso de la anotación @Query es bastante simple y directo. Simplemente,
se escribe la consulta JPQL como un valor de la anotación @Query.
Ejemplo:
@Query("SELECT u FROM User u WHERE u.lastName = ?1 AND u.email LIKE %?2")
List<User> findByLastNameAndEmailDomain(String lastName, String emailDomain);
 */

    @Query("SELECT a FROM Agricultor a WHERE a.edadagricultor = :edad")
    List<Agricultor> findAgricultoresByEdad(Integer edad);
    /*
    La misma consulta con numeros como parametros
     @Query("SELECT a FROM Agricultor a WHERE a.edadagricultor = ?1")
    List<Agricultor> findAgricultoresByEdad(Integer edad);
     */


    /*
    La anotación @Query también puede ser utilizada para ejecutar consultas nativas SQL,
     simplemente pasando el atributo nativeQuery = true a la anotación. Por ejemplo:

     @Query(value = "SELECT * FROM Users u WHERE u.last_name = ?1 AND u.email LIKE %?2", nativeQuery = true)
    List<User> findByLastNameAndEmailDomain(String lastName, String emailDomain);

        Aquí se está ejecutando una consulta SQL nativa en lugar de una consulta JPQL
     */
}

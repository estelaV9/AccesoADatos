package com.example.esteladevega_practicaapi.repository;

import com.example.esteladevega_practicaapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.Optional;

// EL REPOSITORY SIRVE PARA HACER CONSULTAS PERSONALIZADAS
public interface UserRepository extends JpaRepository<User, Integer> {
    @Query("SELECT u FROM User u WHERE u.name = :name AND u.password = :password")
    Optional<User> findUser(
            @Param("name") String name,
            @Param("password") String password
    ); // QUERY PERSONALIZADA PARA SABER SI UN USUARIO EXISTE
}

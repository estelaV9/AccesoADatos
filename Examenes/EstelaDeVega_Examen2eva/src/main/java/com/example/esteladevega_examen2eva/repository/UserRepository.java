package com.example.esteladevega_examen2eva.repository;

import com.example.esteladevega_examen2eva.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

// EL REPOSITORY SIRVE PARA HACER CONSULTAS PERSONALIZADAS
public interface UserRepository extends JpaRepository<User, Integer> {
    @Query("SELECT u FROM User u WHERE u.username = :name AND u.password = :password")
    Optional<User> findUser(String name, String password
    ); // QUERY PERSONALIZADA PARA SABER SI UN USUARIO EXISTE
}

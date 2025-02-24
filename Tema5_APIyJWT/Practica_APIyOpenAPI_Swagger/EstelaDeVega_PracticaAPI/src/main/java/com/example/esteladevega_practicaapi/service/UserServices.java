package com.example.esteladevega_practicaapi.service;

import com.example.esteladevega_practicaapi.model.User;
import com.example.esteladevega_practicaapi.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServices {
    private final UserRepository userRepository; // REPOSITORIO HABITACION

    public UserServices(UserRepository userRepository) {
        this.userRepository = userRepository;
    } // CONSTRUCTOR PARA AÑADIR EL REPOSITORIO HABITACION EN EL SERVICIO

    public Optional<User> isExistsUser(String name, String password) {
        return userRepository.findUser(name, password);
    } // METODO PARA SABER SI EXISTE UN USUARIO

    public <S extends User> S save(S entity) {
        return userRepository.save(entity);
    } // METODO PARA GUARDAR UN USUARIO
}

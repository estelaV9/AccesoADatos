package it.juan.user.service;

import it.juan.user.entity.User;

import java.util.List;

/*
Como en el paquete anterior tendremos una clase y una interface.
El servicio será el que hará de intermediario entre el DAO y el controlador
(La clase que gestionará las peticiones de la API que veremos más adelante).
 */

/*
En el MVC El Controlador, que actúa como intermediario entre el Modelo y la Vista,
gestionando el flujo de información entre ellos y las transformaciones para adaptar
los datos a las necesidades de cada uno.

Aqui el servicio hace de intermediario entre e DAO y el controlador
 */

public interface UserService {

    public List<User> findAll();

    public User findById(int id);

    public void save(User user);

    public void deleteById(int id);
}

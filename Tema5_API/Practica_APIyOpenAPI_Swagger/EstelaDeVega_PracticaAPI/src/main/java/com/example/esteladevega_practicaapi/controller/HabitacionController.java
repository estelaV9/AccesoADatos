package com.example.esteladevega_practicaapi.controller;

import com.example.esteladevega_practicaapi.model.Habitacion;
import com.example.esteladevega_practicaapi.service.HabitacionServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController // CONTROLADOR REST
@RequestMapping("/api/habitacion") // RUTA BASE DE LAS PETICIONES DEL CONTROLADOR DE HABITACION
public class HabitacionController {
    private final HabitacionServices habitacionServices; // SERVICIO HABITACION

    public HabitacionController(HabitacionServices habitacionServices) {
        this.habitacionServices = habitacionServices;
    } // CONSTRUCTOR PARA AÑADIR EL SERVICIO HOTEL EN EL CONTROLADOR

    @PostMapping("/save")
    public ResponseEntity<?> createHabitacion(@RequestBody Habitacion habitacion) {
        habitacionServices.save(habitacion); // SE GUARDA LA HABITACION
        // SE RETORNA UNA RESPUESTA HTTP CON EL ESTADO CREADO
        return new ResponseEntity<>(HttpStatus.CREATED);
    } // METODO PARA CREAR UNA HABITACION, MANEJANDO LA PETICION HTTP POST EN LA RUTA "/save"
}

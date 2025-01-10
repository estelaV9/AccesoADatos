package com.example.esteladevega_practicaapi.controller;

import com.example.esteladevega_practicaapi.model.Habitacion;
import com.example.esteladevega_practicaapi.model.Hotel;
import com.example.esteladevega_practicaapi.service.HabitacionServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController // CONTROLADOR REST
@RequestMapping("/api/habitacion") // RUTA BASE DE LAS PETICIONES DEL CONTROLADOR DE HABITACION
public class HabitacionController {
    private final HabitacionServices habitacionServices; // SERVICIO HABITACION

    public HabitacionController(HabitacionServices habitacionServices) {
        this.habitacionServices = habitacionServices;
    } // CONSTRUCTOR PARA AÑADIR EL SERVICIO HOTEL EN EL CONTROLADOR

    @GetMapping("/all")
    public List<Habitacion> getAllHabitaciones() {
        try {
            // SE LLAMA AL SERVICIO PARA OBTENER TODAS LAS HABITACIONES
            return habitacionServices.findAll();
        } catch (Exception e) {
            // SE MANEJA UNA EXCEPCION Y SE LANZA UN BAD REQUEST SI FALLA
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Error al listar todas las habitacioens ", e);
        }
    } // METODO PARA LISTAR TODAS LAS HABITACIONES, PETICION HTTP GET EN LA RUTA "/all"

    @PostMapping("/save")
    public ResponseEntity<?> createHabitacion(@RequestBody Habitacion habitacion) {
        habitacionServices.save(habitacion); // SE GUARDA LA HABITACION
        // SE RETORNA UNA RESPUESTA HTTP CON EL ESTADO CREADO
        return new ResponseEntity<>(HttpStatus.CREATED);
    } // METODO PARA CREAR UNA HABITACION, MANEJANDO LA PETICION HTTP POST EN LA RUTA "/save"

    @DeleteMapping("/delete/{idHabitacion}")
    public ResponseEntity<?> deleteHabitacionByHotel(@PathVariable int idHabitacion){
        habitacionServices.deleteById(idHabitacion); // SE ELIMNA LA HABITACION
        // SE RETORNA UNA RESPUESTA HTTP CON EL ESTADO CREADO
        return new ResponseEntity<>(HttpStatus.CREATED);
    } // METODO PARA ELIMINAR UNA HABITACION DETERMINADA DE UN HOTEL
}

package com.example.esteladevega_practicaapi.controller;

import com.example.esteladevega_practicaapi.model.Habitacion;
import com.example.esteladevega_practicaapi.service.HabitacionServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

/* {
  "tamanio": 1,
  "precioNoche": 23323.50,
  "desayuno": true,
  "ocupada": false,
  "localidad": "afdsfasd",
  "hotel": {
    "idHotel": 1
  }
} */

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
    public ResponseEntity<?> deleteHabitacionByHotel(@PathVariable int idHabitacion) {
        habitacionServices.deleteById(idHabitacion); // SE ELIMNA LA HABITACION
        // SE RETORNA UNA RESPUESTA HTTP CON EL ESTADO CREADO
        return new ResponseEntity<>(HttpStatus.CREATED);
    } // METODO PARA ELIMINAR UNA HABITACION DETERMINADA DE UN HOTEL

    @PostMapping("/update/{idHabitacion}")
    public ResponseEntity<?> updateOcupada(@PathVariable int idHabitacion) {
        // ASIGNAMOS LA HABITACION BUSCADA POR EL ID
        Optional<Habitacion> habitacion = habitacionServices.findById(idHabitacion);

        if (habitacion.isPresent()) {
            // CREAMOS UN OBJETO CON LOS ATRIBUTOS DE LA HABITACION OBTENIDA
            Habitacion hb = habitacion.get();
            hb.setOcupada(true); // CAMBIAMOS EL VALOR A QUE ESTA OCUPADA
            habitacionServices.save(hb); // SE GUARDA

            // SE RETORNA UNA RESPUESTA HTTP CON EL ESTADO CREADO
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } // SI NO ESTA VACIO LA HABITACION, SE MODIFICA Y SE GUARDA
    } // METODO PARA MODIFICAR UNA HABITACION PARA INDICAR QUE ESTA OCUPADA


    // http://localhost:9999/api/habitacion/habitacionesLibres/1/0/100/21/400
    @GetMapping("/habitacionesLibres/{idHotel}/{tamanioMin}/{tamanioMax}/{precioMin}/{precioMax}")
    public ResponseEntity<List<Habitacion>> buscarHabitacionesLibres(
            @PathVariable int idHotel,
            @PathVariable int tamanioMin,
            @PathVariable int tamanioMax,
            @PathVariable double precioMin,
            @PathVariable double precioMax) {

        // LISTA CON LAS HABITACIONES DISPONIBLES
        List<Habitacion> habitaciones = habitacionServices.buscarHabitacionesLibresPorRango(idHotel, tamanioMin, tamanioMax, precioMin, precioMax);

        return ResponseEntity.ok(habitaciones);
    } // METODO PERSONALIZADO PARA HACER UNA BUSQUEDA DE HABITACIONES POR TAMAÑO Y PRECIO Y QUE ESTEN LIBRES
}

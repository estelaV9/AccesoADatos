package com.example.esteladevega_practicaapi.controller;

import com.example.esteladevega_practicaapi.model.Hotel;
import com.example.esteladevega_practicaapi.service.HotelServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

/* {
    "nombreHotel": "Hotel Playa Dorada",
    "descripcion": "Un hermoso hotel frente a la playa con todas las comodidades.",
    "categoria": "5 estrellas",
    "piscina": true,
    "localidad": "Playa del Carmen",
    "habitacionList": []
  } */

@RestController // CONTROLADOR REST
@RequestMapping("/api/hotel") // RUTA BASE DE LAS PETICIONES DEL CONTROLADOR DE HOTEL
public class HotelController {
    private final HotelServices hotelServices; // SERVICIO HOTEL

    public HotelController(HotelServices hotelServices) {
        this.hotelServices = hotelServices;
    } // CONSTRUCTOR PARA AÑADIR EL SERVICIO HOTEL EN EL CONTROLADOR

    @PostMapping("/save")
    public ResponseEntity<?> createHotel(@RequestBody Hotel hotel) {
        try {
            hotelServices.save(hotel); // SE GUARDA EL HOTEL USANDO EL SERVICIO HOTEL
            // SE RETORNA UNA RESPUESTA HTTP CON EL ESTADO CREADO (si se guardó correctamente)
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            // SI HAY UN ERROR AL GUARDAR EL HOTEL, SE MANEJA LA EXCEPCION Y SE DEVUELVE UN MENSAJE DE ERROR
            return new ResponseEntity<>("Error al guardar el hotel: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    } // METODO PARA CREAR UN HOTEL, MANEJANDO LA PETICION HTTP POST EN LA RUTA "/save"


    @GetMapping("/all")
    public ResponseEntity<?> getAllHoteles() {
        try {
            // SE LLAMA AL SERVICIO PARA OBTENER TODOS LOS HOTELES
            List<Hotel> hoteles = hotelServices.findAll();

            if(hoteles.isEmpty()){
                return new ResponseEntity<>("No existe ningun hotel", HttpStatus.NOT_FOUND);
            } // SI NO HAY HOTELES, DEVUELVE UN MENSAJE

            return new ResponseEntity<>(hoteles, HttpStatus.OK);
        } catch (Exception e) {
            // SE MANEJA UNA EXCEPCION Y SE LANZA UN BAD REQUEST SI FALLA
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Error al listar todos los hoteles ", e);
        }
    } // METODO PARA LISTAR TODOS LOS HOTELES, PETICION HTTP GET EN LA RUTA "/all"

    @GetMapping("/localidad/{localidad}")
    public ResponseEntity<?> findHotelByLocalidad(@PathVariable String localidad){
        try{
            List<Hotel> hoteles = hotelServices.findHotelByLocalidad(localidad);

            if(hoteles.isEmpty()){
                return new ResponseEntity<>("No existe ningun hotel por esa localidad", HttpStatus.NOT_FOUND);
            } // SI NO HAY HOTELES, DEVUELVE UN MENSAJE

            return new ResponseEntity<>(hoteles, HttpStatus.OK);

        } catch (Exception e){
            // SE MANEJA UNA EXCEPCION Y SE LANZA UN BAD REQUEST SI FALLA
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Error buscar hotel por localidad: ", e);
        }
    } // METODO PARA BUSCAR UN HOTEL POR LOCALIDAD, PETICION HTTP GET EN LA RUTA "/localidad"

    @GetMapping("/categoria/{categoria}")
    public ResponseEntity<?> findHotelByCategoria(@PathVariable String categoria){
        try{
            List<Hotel> hoteles = hotelServices.findHotelByCategoria(categoria);

            if(hoteles.isEmpty()){
                return new ResponseEntity<>("No existe ningun hotel por esa categoria", HttpStatus.NOT_FOUND);
            } // SI NO HAY HOTELES, DEVUELVE UN MENSAJE

            return new ResponseEntity<>(hoteles, HttpStatus.OK);
        } catch (Exception e){
            // SE MANEJA UNA EXCEPCION Y SE LANZA UN BAD REQUEST SI FALLA
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Error buscar hotel por categoria: ", e);
        }
    } // METODO PARA BUSCAR UN HOTEL POR CATEGORIA, PETICION HTTP GET EN LA RUTA "/categoria"
}

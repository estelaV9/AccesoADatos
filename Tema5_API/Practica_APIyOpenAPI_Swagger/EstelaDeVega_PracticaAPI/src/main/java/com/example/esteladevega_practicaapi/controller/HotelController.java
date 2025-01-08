package com.example.esteladevega_practicaapi.controller;

import com.example.esteladevega_practicaapi.model.Hotel;
import com.example.esteladevega_practicaapi.service.HotelServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@RestController // CONTROLADOR REST
@RequestMapping("/api/hotel") // RUTA BASE DE LAS PETICIONES DEL CONTROLADOR DE HOTEL
public class HotelController {
    private final HotelServices hotelServices; // SERVICIO HOTEL

    public HotelController(HotelServices hotelServices) {
        this.hotelServices = hotelServices;
    } // CONSTRUCTOR PARA AÑADIR EL SERVICIO HOTEL EN EL CONTROLADOR

    @PostMapping("/save")
    public ResponseEntity<?> createHotel(@RequestBody Hotel hotel) {
        hotelServices.save(hotel); // SE GUARDA EL HOTEL USANDO EL SERVICIO HOTEL
        // SE RETORNA UNA RESPUESTA HTTP CON EL ESTADO CREADO
        return new ResponseEntity<>(HttpStatus.CREATED);
    } // METODO PARA CREAR UN HOTEL, MANEJANDO LA PETICION HTTP POST EN LA RUTA "/save"


    @GetMapping("/all")
    public List<Hotel> getAllHoteles() {
        try {
            // SE LLAMA AL SERVICIO PARA OBTENER TODOS LOS HOTELES
            return hotelServices.findAll();
        } catch (Exception e) {
            // SE MANEJA UNA EXCEPCION Y SE LANZA UN BAD REQUEST SI FALLA
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Error al listar todos los hoteles ", e);
        }
    } // METODO PARA LISTAR TODOS LOS HOTELES, PETICION HTTP GET EN LA RUTA "/all"

    @GetMapping("/localidad")
    public List<Hotel> findHotelByLocalidad(String localidad){
        try{
            return hotelServices.findHotelByLocalidad(localidad);
        } catch (Exception e){
            // SE MANEJA UNA EXCEPCION Y SE LANZA UN BAD REQUEST SI FALLA
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Error buscar hotel por localidad: ", e);
        }
    } // METODO PARA BUSCAR UN HOTEL POR LOCALIDAD, PETICION HTTP GET EN LA RUTA "/localidad"

}

package com.example.esteladevega_examen2eva.controller;

import com.example.esteladevega_examen2eva.model.Vuelo;
import com.example.esteladevega_examen2eva.service.VueloServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/vuelos")
public class VueloController {
    private final VueloServices vueloServices;

    public VueloController(VueloServices vueloServices) {
        this.vueloServices = vueloServices;
    }

    @GetMapping("/buscarvuelos")
    public ResponseEntity<?> buscarVuelos(@RequestParam String origen, @RequestParam String destino, @RequestParam int numeroescalas) {
        try {
            // SE LLAMA AL SERVICIO PARA OBTENER TODOS LOS VUELOS SEGUN LOS FILTROS
            List<Vuelo> vuelos = vueloServices.buscarVuelos(origen, destino, numeroescalas);
            System.out.printf(vuelos.toString());
            if (vuelos.isEmpty()) {
                return new ResponseEntity<>("No existe ningun vuelo", HttpStatus.NOT_FOUND);
            } // SI NO HAY VUELOS, DEVUELVE UN MENSAJE

            return new ResponseEntity<>(vuelos.toString(), HttpStatus.OK);
        } catch (Exception e) {
            // SE MANEJA UNA EXCEPCION Y SE LANZA UN BAD REQUEST SI FALLA
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Error al listar todos los vuelos ", e);
        }
    } // METODO PARA LISTAR LOS VUELOS SEGUN EL ORIGEN, DESTINO Y NUMERO DE ESCALASS

    @GetMapping("/buscardestino")
    public ResponseEntity<?> buscarVuelosDestino(@RequestParam String destino) {
        try {
            // SE LLAMA AL SERVICIO PARA OBTENER TODOS LOS VUELOS SEGUN EL DESTINO
            List<Vuelo> vuelos = vueloServices.buscarVuelosDestino(destino);

            if (vuelos.isEmpty()) {
                return new ResponseEntity<>("No existe ningun vuelo con ese destino", HttpStatus.NOT_FOUND);
            } // SI NO HAY VUELOS, DEVUELVE UN MENSAJE

            return new ResponseEntity<>(vuelos.toString(), HttpStatus.OK);
        } catch (Exception e) {
            // SE MANEJA UNA EXCEPCION Y SE LANZA UN BAD REQUEST SI FALLA
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Error al listar todos los vuelos del destino", e);
        }
    } // METODO PARA LISTAR LOS VUELOS CON UN DESTINO

    @GetMapping("/numero-vuelos")
    public ResponseEntity<?> numeroVuelosOrigen(@RequestParam String origen) {
        try {
            // SE LLAMA AL SERVICIO PARA OBTENER TODOS LOS VUELOS SEGUN LOS FILTROS
            int vuelos = vueloServices.numeroVuelosOrigen(origen);

            if (vuelos == 0) {
                return new ResponseEntity<>("No existe ningun vuelo con ese origen", HttpStatus.NOT_FOUND);
            } // SI NO HAY VUELOS, DEVUELVE UN MENSAJE

            return new ResponseEntity<>(vuelos, HttpStatus.OK);
        } catch (Exception e) {
            // SE MANEJA UNA EXCEPCION Y SE LANZA UN BAD REQUEST SI FALLA
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Error al listar todos los vuelos del origen", e);
        }
    } // METODO PARA CONTAR LOS VUELOS QUE TIENE UN ORIGEN
}
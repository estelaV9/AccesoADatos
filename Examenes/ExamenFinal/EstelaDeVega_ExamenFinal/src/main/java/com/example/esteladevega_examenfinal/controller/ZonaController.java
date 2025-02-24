package com.example.esteladevega_examenfinal.controller;

import com.example.esteladevega_examenfinal.model.Zona;
import com.example.esteladevega_examenfinal.service.ZonaServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/zonas")
public class ZonaController {
    private final ZonaServices zonaServices;

    public ZonaController(ZonaServices zonaServices) {
        this.zonaServices = zonaServices;
    }

    @PostMapping("/save")
    public ResponseEntity<?> createZona(@RequestBody Zona zona) {
        try {
            zonaServices.save(zona); // SE GUARDA LA ZONA
            // SE RETORNA UNA RESPUESTA HTTP CON EL ESTADO CREADO (si se guardó correctamente)
            return new ResponseEntity<>("Se ha creado correctamente", HttpStatus.CREATED);
        } catch (Exception e) {
            // SI HAY UN ERROR AL GUARDAR LA ZONA, SE MANEJA LA EXCEPCION Y SE DEVUELVE UN MENSAJE DE ERROR
            return new ResponseEntity<>("Error al guardar la zona: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    } // METODO PARA CREAR UNA ZONA

    @GetMapping("/list")
    public ResponseEntity<?> listarZonas() {
        try {
            // SE LLAMA AL SERVICIO PARA OBTENER TODOS LAS ZONAS
            List<Zona> zona = zonaServices.findAll();

            if (zona.isEmpty()) {
                return new ResponseEntity<>("No hay ninguna zona todavia", HttpStatus.NOT_FOUND);
            } // SI NO HAY ZONAS, DEVUELVE UN MENSAJE

            return new ResponseEntity<>(zonaServices.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            // SE MANEJA UNA EXCEPCION Y SE LANZA UN BAD REQUEST SI FALLA
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Error al listar todas las zonas ", e);
        }
    } // METODO PARA LISTAR LAS ZONAS PARA PRUEBAS
}
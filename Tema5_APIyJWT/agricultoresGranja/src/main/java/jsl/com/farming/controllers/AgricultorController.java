package jsl.com.farming.controllers;

import jsl.com.farming.entities.Agricultor;
import jsl.com.farming.entities.Granja;
import jsl.com.farming.service.AgricultorServices;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/granja")
public class AgricultorController {
    private final AgricultorServices agricultorServices;

    public AgricultorController(AgricultorServices agricultorServices) {

        this.agricultorServices = agricultorServices;
    }

    @GetMapping("/")
    public List<Agricultor> getAllAgricultores() {

        try {
            return agricultorServices.findAllAgricultores();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error al obtener todos los agricultores", e);
        }

    }

    //ResponseEntity  se utiliza para ver ms informacion cuando hagamos una peticion
    //ResponseEntity- una clase especial para devolver respuestas.
    // Al usarlo, luego podemos devolver el código de estado HTTP al cliente
    @GetMapping("{idAgricultor}")
    public ResponseEntity<?> getAgricultor(@PathVariable int idAgricultor) {

        return ResponseEntity.of(agricultorServices.findAgricultorById(idAgricultor));
    }

    @GetMapping("edad/{edadAgricultor}")
    public ResponseEntity<List<Agricultor>> edadAgricultor(@PathVariable int edadAgricultor) {

        return ResponseEntity.ok(agricultorServices.findAgricultoresByEdad(edadAgricultor));
    }
    /*@GetMapping("edad/{edadAgricultor}")
    public List<Agricultor> edadAgricultor(@PathVariable int edadAgricultor) {

        try {
            return agricultorServices.findAgricultoresByEdad(edadAgricultor);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error al obtener todos los agricultores", e);
        }


    }*/

    @PostMapping("save")
    public ResponseEntity<?> createAgricultor(@RequestBody Agricultor agricultor) {
        agricultorServices.saveAgricultor(agricultor);
        return new ResponseEntity<>(HttpStatus.CREATED);


    }

    @PutMapping("edit")
    public ResponseEntity<?> updateAgricultor(@RequestBody Agricultor agricultor) {
        agricultorServices.updateAgricultor(agricultor);
        return new ResponseEntity<>(HttpStatus.CREATED);

    }


    @DeleteMapping("{idAgricultor}")
    public ResponseEntity<?> deleteAgricultor(@PathVariable int idAgricultor) {
        agricultorServices.deleteAgricultorById(idAgricultor);
        return new ResponseEntity<>(HttpStatus.CREATED);

    }
}


package com.example.esteladevega_examen2eva.controller;

import com.example.esteladevega_examen2eva.model.Compañia;
import com.example.esteladevega_examen2eva.service.CompañiaServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/compañia")
public class CompañiaController {
    private final CompañiaServices compañiaServices;

    public CompañiaController(CompañiaServices compañiaServices) {
        this.compañiaServices = compañiaServices;
    }

    @PostMapping("/save")
    public ResponseEntity<?> createCompañia(@RequestParam String nombreCompañia) {
        try {
            Compañia compañia = new Compañia(nombreCompañia);
            compañiaServices.save(compañia); // SE GUARDA LA COMPAÑIA
            // SE RETORNA UNA RESPUESTA HTTP CON EL ESTADO CREADO (si se guardó correctamente)
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            // SI HAY UN ERROR AL GUARDAR LA COMPAÑIA, SE MANEJA LA EXCEPCION Y SE DEVUELVE UN MENSAJE DE ERROR
            return new ResponseEntity<>("Error al guardar la compañia: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    } // METODO PARA CREAR UNA COMPAÑIA

    @DeleteMapping("/delete/{idCompañia}")
    public ResponseEntity<?> deleteCompañia(@PathVariable int idCompañia) {
        try {
            Optional<Compañia> compañia = compañiaServices.findById(idCompañia);
            if(compañia.isEmpty()){
                // SE RETORNA UNA RESPUESTA HTTP PARA DECIR QUE NO SE ENCONTRO UNA COMPAÑIA CON ESE ID
                return new ResponseEntity<>("No existe una compañia con ese id", HttpStatus.BAD_REQUEST);
            } // VERIFICAMOS SI EL ID DE ESA COMPAÑIA EXISTE

            compañiaServices.deleteById(idCompañia); // SE ELIMINA LA COMPAÑIA
            // SE RETORNA UNA RESPUESTA HTTP CON EL ESTADO ELIMINADO (si se eliminó correctamente)
            return new ResponseEntity<>("Se elimino correctamente", HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            // SI HAY UN ERROR AL ELIMINAR LA COMPAÑIA, SE MANEJA LA EXCEPCION Y SE DEVUELVE UN MENSAJE DE ERROR
            return new ResponseEntity<>("Error al eliminar la compañia: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    } // METODO PARA ELIMINAR UNA COMPAÑIA

    @PostMapping("/update/{idCompañia}")
    public ResponseEntity<?> updateCompañia(@PathVariable int idCompañia, @RequestParam String nombreCompañia) {
        // VERIFICAMOS SI EXISTE UNA COMPAÑIA CON ESE ID
        Optional<Compañia> compañia = compañiaServices.findById(idCompañia);

        if (compañia.isPresent()) {
            // CREAMOS UN OBJETO CON LOS ATRIBUTOS DE LA COMPAÑIA OBTENIDA
            Compañia compañia1 = compañia.get();

            compañia1.setNombrecompañia(nombreCompañia); // CAMBIAMOS EL NOMBRE DE LA COMPAÑIA
            compañiaServices.save(compañia1); // SE GUARDA

            // SE RETORNA UNA RESPUESTA HTTP CON EL ESTADO CREADO
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Compañia no encontrada", HttpStatus.NOT_FOUND);
        } // SI NO ESTA VACIO LA COMPAÑIA, SE MODIFICA Y SE GUARDA
    } // METODO PARA MODIFICAR EL NOMBRE DE UNA COMPAÑIA
}
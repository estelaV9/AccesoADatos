package com.example.esteladevega_examenfinal.controller;


import com.example.esteladevega_examenfinal.model.Piso;
import com.example.esteladevega_examenfinal.service.PisoServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/pisos")
public class PisoController {
    private final PisoServices pisoServices;

    public PisoController(PisoServices pisoServices) {
        this.pisoServices = pisoServices;
    }

    @GetMapping("/buscar-mayor-100-metros-y-no-reservado")
    public ResponseEntity<?> buscarPorMetrosYReservado() {
        try {
            // SE LLAMA AL SERVICIO PARA OBTENER TODOS LOS PISOS SEGUN LOS FILTROS
            List<Piso> pisos = pisoServices.findPisoByMetrosAndReservado();

            if (pisos.isEmpty()) {
                return new ResponseEntity<>("No existe ningun piso con mas de 100m que NO esten reservados", HttpStatus.NOT_FOUND);
            } // SI NO HAY PISOS, DEVUELVE UN MENSAJE

            return new ResponseEntity<>(pisoServices.findPisoByMetrosAndReservado(), HttpStatus.OK);
        } catch (Exception e) {
            // SE MANEJA UNA EXCEPCION Y SE LANZA UN BAD REQUEST SI FALLA
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Error al listar los inmuebles con mas de 100 metros y que NO esten reservados ", e);
        }
    } // METODO PARA LISTAR LOS PISOS SEGUN LOS METROS Y QUE NO ESTE RESERVADO

    @GetMapping("/buscar-alquiler-menor-500-no-reservado")
    public ResponseEntity<?> buscarPorAlquilerMetrosYReservado() {
        try {
            // SE LLAMA AL SERVICIO PARA OBTENER TODOS LOS PISOS SEGUN LOS FILTROS
            List<Piso> pisos = pisoServices.findPisoByTipoAndMetrosAndReservado();

            if (pisos.isEmpty()) {
                return new ResponseEntity<>("No existe ningun piso en alquiler, con menos de 500m y que NO esten reservados", HttpStatus.NOT_FOUND);
            } // SI NO HAY PISOS, DEVUELVE UN MENSAJE

            return new ResponseEntity<>(pisoServices.findPisoByTipoAndMetrosAndReservado(), HttpStatus.OK);
        } catch (Exception e) {
            // SE MANEJA UNA EXCEPCION Y SE LANZA UN BAD REQUEST SI FALLA
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Error al listar los inmuebles en alquiler, con menos de 500m y que NO esten reservados ", e);
        }
    } // METODO PARA LISTAR LOS PISOS EN ALQUILER, CON MENOS DE 500m Y QUE NO ESTEN RESERVADOS


    @PostMapping("/update/{idPiso}")
    public ResponseEntity<?> updatePrecio(@PathVariable int idPiso, @RequestParam int precio) {
        // ASIGNAMOS EL PISO BUSCADA POR EL ID
        Optional<Piso> piso = pisoServices.findById(idPiso);

        if (piso.isPresent()) {
            // CREAMOS UN OBJETO CON LOS ATRIBUTOS DEL PISO OBTENIDA
            Piso piso1 = piso.get();

            if (piso1.getReservado().equals("Si")) {
                return new ResponseEntity<>("Cambia cambiar el precio a un inmueble, este no debe estar en estado de reservado", HttpStatus.BAD_REQUEST);
            } else {
                piso1.setPrecio(precio);
                pisoServices.save(piso1); // SE GUARDA

                // SE RETORNA UNA RESPUESTA HTTP CON EL ESTADO CREADO
                return new ResponseEntity<>("Se modifico correctamente el precio", HttpStatus.OK);
            } // VERIFICAMOS QUE ESTE INMUEBLE NO PUEDA ESTAR RESERVADO

        } else {
            return new ResponseEntity<>("Piso no encontrada", HttpStatus.NOT_FOUND);
        } // SI NO ESTA VACIO EL PISO, SE MODIFICA Y SE GUARDA
    } // METODO PARA MODIFICAR EL PRECIO DE UN PISO


    @DeleteMapping("/delete/{idPiso}")
    public ResponseEntity<?> deletePiso(@PathVariable int idPiso) {
        try {
            // ASIGNAMOS EL PISO BUSCADA POR EL ID
            Optional<Piso> piso = pisoServices.findById(idPiso);

            if (piso.isPresent()) {
                // CREAMOS UN OBJETO CON LOS ATRIBUTOS DEL PISO OBTENIDA
                Piso piso1 = piso.get();

                if (piso1.getReservado().equals("Si")) {
                    return new ResponseEntity<>("No se puedo eliminar ya que esta en estado de reservado", HttpStatus.BAD_REQUEST);
                } else {
                    pisoServices.deleteById(idPiso); // SE ELIMINA EL PISO
                    // SE RETORNA UNA RESPUESTA HTTP CON EL ESTADO ELIMINADO (si se eliminó correctamente)
                    return new ResponseEntity<>("Se ha eliminado correctamente el piso", HttpStatus.OK);
                } // VERIFICAMOS QUE ESTE INMUEBLE NO ESTE RESERVADO

            } else {
                return new ResponseEntity<>("Piso no encontrada", HttpStatus.NOT_FOUND);
            } // SI NO ESTA VACIO EL PISO, SE VERIFICA QUE NO ESTE RESERVADO Y SE ELIMINA
        } catch (Exception e) {
            // SI HAY UN ERROR AL ELIMINAR EL PISO, SE MANEJA LA EXCEPCION Y SE DEVUELVE UN MENSAJE DE ERROR
            return new ResponseEntity<>("Error al eliminar el piso: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    } // METODO PARA ELIMINAR UN PISO POR SU ID
}
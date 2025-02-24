package com.example.esteladevega_examen2eva.service;

import com.example.esteladevega_examen2eva.model.Compañia;
import com.example.esteladevega_examen2eva.repository.CompañiaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CompañiaServices {
    private final CompañiaRepository compañiaRepository; // REPOSITORIO COMPAÑIA

    public CompañiaServices(CompañiaRepository compañiaRepository) {
        this.compañiaRepository = compañiaRepository;
    } // CONSTRUCTOR PARA AÑADIR EL REPOSITORIO COMPAÑIA EN EL SERVICIO

    public <S extends Compañia> S save(S entity) {
        return compañiaRepository.save(entity);
    } // METODO PARA GUARDAR LA COMPAÑIA

    public void deleteById(Integer integer) {
        compañiaRepository.deleteById(integer);
    } // METODO PARA ELIMINAR UNA COMPAÑIA POR SU ID

    /*public Compañia modificarCompania(int id, Compañia compania) {
        compania.setIdcompañia(id);
        return compañiaRepository.save(compania);
    } // METODO PARA MODIFICAR EL NOMBRE DE LA COMPAÑIA POR ID*/

    public Optional<Compañia> findById(Integer integer) {
        return compañiaRepository.findById(integer);
    } // METODO PARA OBTENER POR ID
}
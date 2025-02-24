package com.example.esteladevega_examenfinal.service;

import com.example.esteladevega_examenfinal.model.Piso;
import com.example.esteladevega_examenfinal.repository.PisoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PisoServices {
    private final PisoRepository pisoRepository; // REPOSITORIO PISO


    public PisoServices(PisoRepository pisoRepository) {
        this.pisoRepository = pisoRepository;
    } // CONSTRUCTOR PARA AÑADIR EL REPOSITORIO PISO EN EL SERVICIO

    public List<Piso> findPisoByMetrosAndReservado() {
        return pisoRepository.findPisoByMetrosAndReservado();
    } // BUSCAR PISO POR METROS Y POR NO RESERVADO

    public List<Piso> findPisoByTipoAndMetrosAndReservado() {
        return pisoRepository.findPisoByTipoAndMetrosAndReservado();
    } // BUSCAR PISO EN ALQUILER, MENOR O IGUAL DE 500 METROS Y QUE NO ESTE RESREVADO

    public Optional<Piso> findById(Integer integer) {
        return pisoRepository.findById(integer);
    } // OBTENER POR ID

    public <S extends Piso> S save(S entity) {
        return pisoRepository.save(entity);
    } // GUARDAR PISO

    public void deleteById(Integer integer) {
        pisoRepository.deleteById(integer);
    } // ELIMINAR PISO POR ID
}

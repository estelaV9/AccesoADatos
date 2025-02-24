package com.example.esteladevega_examenfinal.service;

import com.example.esteladevega_examenfinal.model.Zona;
import com.example.esteladevega_examenfinal.repository.ZonaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ZonaServices {
    private final ZonaRepository zonaRepository; // REPOSITORIO ZONA


    public ZonaServices(ZonaRepository zonaRepository) {
        this.zonaRepository = zonaRepository;
    } // CONSTRUCTOR PARA AÑADIR EL REPOSITORIO ZONA EN EL SERVICIO

    public List<Zona> findAll() {
        return zonaRepository.findAll();
    } // LISTAR LAS ZONAS

    public <S extends Zona> S save(S entity) {
        return zonaRepository.save(entity);
    } // CREAR UNA ZONA
}

package com.example.esteladevega_examen2eva.service;

import com.example.esteladevega_examen2eva.model.Vuelo;
import com.example.esteladevega_examen2eva.repository.VueloRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VueloServices {
    private final VueloRepository vueloRepository; // REPOSITORIO VUELO

    public VueloServices(VueloRepository vueloRepository) {
        this.vueloRepository = vueloRepository;
    } // CONSTRUCTOR PARA AÑADIR EL REPOSITORIO VUELO EN EL SERVICIO

    public List<Vuelo> buscarVuelos(String origen, String destino, int numeroescaladas) {
        return vueloRepository.findVueloByOrigenDestinoNumeroescalas(origen, destino, numeroescaladas);
    } // METODO PARA BUSCAR VUELOS FILTRANDO POR ORIGEN, DESTINO Y NUMERO DE ESCALAS

    public List<Vuelo> buscarVuelosDestino(String destino) {
        return vueloRepository.findVueloByDestinos(destino);
    } // METODO PARA LISTAR LOS VUELOS SEGUN EL DESTINO

    public int numeroVuelosOrigen(String origen) {
        return vueloRepository.findVueloByOrigen(origen);
    } // METODO PARA CONTAR LOS VUELOS QUE TIENE UN ORIGEN

    public List<Vuelo> deleteVueloByCompañia(int idcompañia) {
        return vueloRepository.deleteVueloByCompañia(idcompañia);
    } // METODO PARA ELIMINAR LOS VUELOIS QUE CONTIENEN LASS COMPAÑIAS
}
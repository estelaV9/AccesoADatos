package com.example.esteladevega_examen2eva.repository;

import com.example.esteladevega_examen2eva.model.Vuelo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VueloRepository extends JpaRepository<Vuelo, Integer> {
    @Query("select v from Vuelo v where v.origen = :origen AND v.destino = :destino AND v.numeroescalas = :numeroescalas")
    List<Vuelo> findVueloByOrigenDestinoNumeroescalas(String origen, String destino, int numeroescalas);
    // QUERY PERSONALIZADA PARA BUSCAR VUELOS FILTRANDO POR ORIGEN, DESTINO, NUMERO DE ESCALAS

    @Query("select v from Vuelo v where v.destino = :destino")
    List<Vuelo> findVueloByDestinos(String destino);
    // QUERY PERSONALIZADA PARA BUSCAR VUELOS FILTRANDO POR DESTINO

    @Query("select COUNT(v) from Vuelo v where v.origen = :origen")
    int findVueloByOrigen(String origen);
    // QUERY PERSONALIZADA PARA BUSCAR CUANTOS VUELOS SALEN DESDE UN ORIGEN

    @Query("DELETE from Vuelo v where v.compañia.idcompañia = :idcompañia")
    List<Vuelo> deleteVueloByCompañia(int idcompañia);
    // QUERY PERSONALIZADA PARA ELIMINAR LA COMPAÑIA DE LOS VUELOS
}
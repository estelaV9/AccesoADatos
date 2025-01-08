package com.example.esteladevega_practicaapi.repository;

import com.example.esteladevega_practicaapi.model.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

// EL REPOSITORY SIRVE PARA HACER CONSULTAS PERSONALIZADAS
public interface HotelRepository extends JpaRepository<Hotel, Integer> {
    @Query("SELECT h FROM Hotel h WHERE h.localidad = :localidad")
    List<Hotel> buscarHotelLocalidad(
            @Param("localidad") String localidad
    ); // QUERY PERSONALIZADA QUE DEVUELVE UNA LISTA DE HOTELES POR LOCALIDAD
}

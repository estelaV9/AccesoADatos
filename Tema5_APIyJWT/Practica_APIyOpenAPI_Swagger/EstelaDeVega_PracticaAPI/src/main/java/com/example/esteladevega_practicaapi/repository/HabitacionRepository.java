package com.example.esteladevega_practicaapi.repository;

import com.example.esteladevega_practicaapi.model.Habitacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

// EL REPOSITORY SIRVE PARA HACER CONSULTAS PERSONALIZADAS
public interface HabitacionRepository extends JpaRepository<Habitacion, Integer> {
    @Query("SELECT h FROM Habitacion h WHERE h.hotel.idHotel = :idHotel " +
            "AND h.tamanio BETWEEN :tamanioMin AND :tamanioMax " +
            "AND h.precioNoche BETWEEN :precioMin AND :precioMax " +
            "AND h.ocupada = false")
    List<Habitacion> buscarHabitacionRangoLibre(
            @Param("idHotel") int idHotel,
            @Param("tamanioMin") int tamanioMin,
            @Param("tamanioMax") int tamanioMax,
            @Param("precioMin") double precioMin,
            @Param("precioMax") double precioMax
    );
    // QUERY PERSONALIZADA QUE BUSCA HABITACIONES POR TAMAÑO Y PRECIO (rango minimo→máximo)
       // SOLO APARECERAN AQUELLAS QUE ESTEN MARCADAS COMO LIBRE
}

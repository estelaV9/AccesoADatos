package com.example.esteladevega_practicaapi.repository;

import com.example.esteladevega_practicaapi.model.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

// EL REPOSITORY SIRVE PARA HACER CONSULTAS PERSONALIZADAS
public interface HotelRepository extends JpaRepository<Hotel, Integer> {
}

package com.example.esteladevega_practicaapi.repository;

import com.example.esteladevega_practicaapi.model.Habitacion;
import org.springframework.data.jpa.repository.JpaRepository;

// EL REPOSITORY SIRVE PARA HACER CONSULTAS PERSONALIZADAS
public interface HabitacionRepository extends JpaRepository<Habitacion, Integer> {
}

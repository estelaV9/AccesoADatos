package com.example.esteladevega_practicaapi.service;

import com.example.esteladevega_practicaapi.model.Habitacion;
import com.example.esteladevega_practicaapi.model.Hotel;
import com.example.esteladevega_practicaapi.repository.HabitacionRepository;
import org.springframework.stereotype.Service;

@Service
public class HabitacionServices {
    private final HabitacionRepository habitacionRepository; // REPOSITORIO HABITACION

    public HabitacionServices(HabitacionRepository habitacionRepository) {
        this.habitacionRepository = habitacionRepository;
    } // CONSTRUCTOR PARA AÑADIR EL REPOSITORIO HABITACION EN EL SERVICIO

    public <S extends Habitacion> S save(S entity) {
        return habitacionRepository.save(entity);
    } // METODO PARA GUARDAR UNA ENTIDAD DE TIPO HABITACION
}

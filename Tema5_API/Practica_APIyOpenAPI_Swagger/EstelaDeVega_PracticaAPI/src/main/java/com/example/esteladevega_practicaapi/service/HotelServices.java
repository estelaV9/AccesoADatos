package com.example.esteladevega_practicaapi.service;

import com.example.esteladevega_practicaapi.model.Hotel;
import com.example.esteladevega_practicaapi.repository.HotelRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelServices {
    private final HotelRepository hotelRepository; // REPOSITORIO HOTEL

    public HotelServices(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    } // CONSTRUCTOR PARA AÑADIR EL REPOSITORIO HOTEL EN EL SERVICIO

    public <S extends Hotel> S save(S entity) {
        return hotelRepository.save(entity);
    } // METODO PARA GUARDAR UNA ENTIDAD DE TIPO HOTEL

    public List<Hotel> findAll() {
        return hotelRepository.findAll();
    } // METODO QUE RETORNA TODOS LOS HOTELES

    public List<Hotel> findHotelByLocalidad(String localidad){
        return hotelRepository.buscarHotelLocalidad(localidad);
    } // METODO PARA BUSCAR HOTELES POR LOCALIDAD
}

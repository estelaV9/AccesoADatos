package com.example.esteladevega_examenfinal.repository;

import com.example.esteladevega_examenfinal.model.Piso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface PisoRepository extends JpaRepository<Piso, Integer> {
    @Query("SELECT p FROM Piso p WHERE p.metros > 100 AND p.reservado = 'No'")
    List<Piso> findPisoByMetrosAndReservado(
    ); // QUERY PERSONALIZADA PARA BUSCAR UN INMUEBLE QUE TENGA MAS DE 100 METROS CUADRADOS Y NO HAYA SIDO RESERVADO

    @Query("SELECT p FROM Piso p WHERE p.tipooperacion = 'Alquiler' AND p.precio <= 500 AND p.reservado = 'No'")
    List<Piso> findPisoByTipoAndMetrosAndReservado(
    ); // QUERY PERSONALIZADA PARA BUSCAR UN INMUEBLE QUE ESTA EN ALQUILER, SU PRECIO SEA MENOR O IGUAL DE 500 Y NO HAYA SIDO RESERVADO
}
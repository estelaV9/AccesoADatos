package jsl.com.farming.service;

import jsl.com.farming.entities.Agricultor;
import jsl.com.farming.entities.Granja;
import jsl.com.farming.repository.AgricultorRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AgricultorServices {
    private final AgricultorRepository agricultorRepository;

    public AgricultorServices(AgricultorRepository agricultorRepository) {
        this.agricultorRepository = agricultorRepository;
    }

    public Agricultor saveAgricultor(Agricultor agricultor) {
        return agricultorRepository.save(agricultor);
    }

    public Optional<Agricultor> findAgricultorById(int idAgricultor) {
        return agricultorRepository.findById(idAgricultor);
    }

    public Agricultor updateAgricultor(Agricultor agricultor) {

        return agricultorRepository.save(agricultor);

    }

    public Agricultor addAgricultor(int farmerId, Granja granja) {
        var agricultor = agricultorRepository.findById(farmerId);
        if (agricultor.isPresent()) {
            var foundFarmer = agricultor.get();
            foundFarmer.addAgricultor(granja);
            return agricultorRepository.save(foundFarmer);
        }
        return null;
    }

    public List<Agricultor> findAllAgricultores() {

        return agricultorRepository.findAll();
    }


    public void deleteAgricultorById(int idAgricultor) {
        agricultorRepository.deleteById(idAgricultor);
    }

    public List<Agricultor> findAgricultoresByEdad(int edad) {
        return agricultorRepository.findAgricultoresByEdad(edad);
    }

}

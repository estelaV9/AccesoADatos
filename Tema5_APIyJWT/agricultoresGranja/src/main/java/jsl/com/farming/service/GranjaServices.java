package jsl.com.farming.service;

import jsl.com.farming.entities.Granja;
import jsl.com.farming.repository.GranjaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GranjaServices {
    private final GranjaRepository granjaRepository;

    public GranjaServices(GranjaRepository granjaRepository) {
        this.granjaRepository = granjaRepository;
    }

    public Optional<Granja> findGranjaById(Integer IdGranja) {
        return granjaRepository.findById(IdGranja);
    }

    public Granja updateGranja(Integer IdGranja, Granja granja) {
        return granjaRepository.save(granja);
    }

    public void deleteFarm(Integer IdGranja) {
        granjaRepository.deleteById(IdGranja);
    }
}
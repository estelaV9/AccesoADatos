package com.example.demoJasperReport.service;

import com.example.demoJasperReport.model.Mascota;
import com.example.demoJasperReport.repository.MascotaRepository;
import com.example.demoJasperReport.util.MascotaReportGenerator;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.util.List;

@Service
public class MascotaServiceImpl implements MascotaService {

    @Autowired
    private MascotaRepository mascotaRepository;

    @Autowired
    private MascotaReportGenerator mascotaReportGenerator;

    @Override
    public List<Mascota> findAll() {
        return mascotaRepository.findAll();
    }

    @Override
    public Mascota findById(Long id) {
        return mascotaRepository.findById(id).get();
    }

    @Override
    public Mascota save(Mascota mascota) {
        return mascotaRepository.save(mascota);
    }

    @Override
    public void deleteById(Long id) {
        mascotaRepository.deleteById(id);
    }

    @Override
    public byte[] exportPdf() throws JRException, FileNotFoundException {
        return mascotaReportGenerator.exportToPdf(mascotaRepository.findAll());
    }

    @Override
    public byte[] exportXls() throws JRException, FileNotFoundException {
        return mascotaReportGenerator.exportToXls(mascotaRepository.findAll());
    }
}

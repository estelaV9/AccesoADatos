package com.example.demoJasperReport.service;

import com.example.demoJasperReport.model.Mascota;
import net.sf.jasperreports.engine.JRException;

import java.io.FileNotFoundException;
import java.util.List;

public interface MascotaService {
    List<Mascota> findAll();

    Mascota findById(Long id);

    Mascota save(Mascota mascota);

    void deleteById(Long id);

    byte[] exportPdf() throws JRException, FileNotFoundException;

    byte[] exportXls() throws JRException, FileNotFoundException;
}

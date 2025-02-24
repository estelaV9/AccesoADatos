package com.example.demoJasperReport.controller;

import com.example.demoJasperReport.model.Mascota;
import com.example.demoJasperReport.service.MascotaService;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.io.FileNotFoundException;

@RestController
@RequestMapping("/mascotas")
public class MascotaController {

    @Autowired
    private MascotaService mascotaService;

    /*@RequestMapping("/")
    public String home(){
        return "Hello World!";
    }*/


    @GetMapping("/")
    public ResponseEntity<?> listAll() {
        try {
            return ResponseEntity.ok(mascotaService.findAll());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error ", e);
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<?> listById(@PathVariable Long id) {
        return ResponseEntity.ok(mascotaService.findById(id));
    }

    @PostMapping("/")
    public ResponseEntity<?> save(@RequestBody Mascota mascota) {
        return ResponseEntity.ok(mascotaService.save(mascota));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        mascotaService.deleteById(id);
        return ResponseEntity.ok(null);
    }

    @GetMapping("/export-pdf")
    public ResponseEntity<byte[]> exportPdf() throws JRException, FileNotFoundException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("mascotasReport", "petsReport.pdf");
        return ResponseEntity.ok().headers(headers).body(mascotaService.exportPdf());
    }

    @GetMapping("/export-xls")
    public ResponseEntity<byte[]> exportXls() throws JRException, FileNotFoundException {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet; charset=UTF-8");
        var contentDisposition = ContentDisposition.builder("attachment")
                .filename("mascotasReport" + ".xls").build();
        headers.setContentDisposition(contentDisposition);
        return ResponseEntity.ok()
                .headers(headers)
                .body(mascotaService.exportXls());
    }

}

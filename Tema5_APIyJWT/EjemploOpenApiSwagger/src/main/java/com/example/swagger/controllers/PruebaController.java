package com.example.swagger.controllers;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test")
public class PruebaController {
    @GetMapping("/")
    public String getAll() {

        return "Hola mundo  ;)";
    }
    /*
    Direccion para ver la documentacion de swagger
    http://localhost:9999/doc/swagger-ui.html
     */
}
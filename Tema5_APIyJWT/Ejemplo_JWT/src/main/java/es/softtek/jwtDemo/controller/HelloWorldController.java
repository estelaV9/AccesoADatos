package es.softtek.jwtDemo.controller;

import org.springframework.web.bind.annotation.*;


@RestController
public class HelloWorldController {

	@GetMapping("hello")
	public String helloWorld(@RequestParam(value="name", defaultValue="World") String name) {

		return "Hello "+name+"!!";
	}

	@GetMapping("categoria/{categoria}")
	public String findEquiposBycategoria(@PathVariable String categoria){
		return "Listado de la categoria "+categoria;
	}
}

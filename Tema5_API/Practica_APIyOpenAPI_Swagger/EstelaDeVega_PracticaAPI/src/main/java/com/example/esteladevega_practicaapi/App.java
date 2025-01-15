package com.example.esteladevega_practicaapi;

import com.example.esteladevega_practicaapi.security.JWTAuthorizationFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@SpringBootApplication
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @EnableWebSecurity
    @Configuration
    class WebSecurityConfig extends WebSecurityConfigurerAdapter {

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.csrf().disable()
                    .addFilterAfter(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
                    .authorizeRequests()
                    // PERMITE QUE LA BUSQUEDA DE HOTEL POR LOCALIDAD O CATEGORIA Y LA BUSQUEDA
                    // DE UN HOTEL POR TAMAÑO Y PRECIO NO REQUIERAN NINGUN AUTENTIFICACION
                    .antMatchers(HttpMethod.GET, "/api/hotel/localidad").permitAll()
                    .antMatchers(HttpMethod.GET, "/api/hotel/categoria").permitAll()
                    .antMatchers(HttpMethod.GET, "/api/habitacion/habitacionesLibres/{idHotel}/{tamanioMin}/{tamanioMax}/{precioMin}/{precioMax}").permitAll()
                    .antMatchers(HttpMethod.POST, "/user").permitAll()

                    // REQUIERE AUTORIZACION
                    .antMatchers(HttpMethod.POST, "/api/habitacion/update/{idHabitacion}").authenticated()
                    .antMatchers(HttpMethod.POST, "/api/habitacion/save").authenticated()
                    .antMatchers(HttpMethod.GET, "/api/habitacion/all").authenticated()
                    .antMatchers(HttpMethod.DELETE, "/api/habitacion/delete/{idHabitacion}").authenticated()

                    .antMatchers(HttpMethod.POST, "/api/hotel/save").authenticated()
                    .antMatchers(HttpMethod.GET, "/api/hotel/all").authenticated()

                    // CUALQUIER SOLICITUD DEBE SER AUTENT  ICADA, DE LO CONTRARIO DEVOLVERA UNA RESPUESTA 401
                    .anyRequest().authenticated();
        }
    }
}
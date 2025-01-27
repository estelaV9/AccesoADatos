package com.example.esteladevega_practicaapi;

import com.example.esteladevega_practicaapi.security.JWTAuthorizationFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@SpringBootApplication
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Configuration
    @EnableWebSecurity
    @EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
    class WebSecurityConfig extends WebSecurityConfigurerAdapter {
        // PERMITIR ACCESO SIN AUTENTICACION
        private static final String[] AUTH_WHITELIST = {
                // -- Swagger UI v2
                "/v2/api-docs",
                "/swagger-resources",
                "/swagger-resources/**",
                "/configuration/ui",
                "/configuration/security",
                "/swagger-ui.html",
                "/webjars/**",
                // -- Swagger UI v3 (OpenAPI)
                "/v3/api-docs/**",
                "/swagger-ui/**",
                "/doc/**"
        };

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.csrf().disable()
                    .addFilterAfter(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
                    .authorizeRequests()
                    // PERMITE QUE LA BUSQUEDA DE HOTEL POR LOCALIDAD O CATEGORIA Y LA BUSQUEDA
                    // DE UN HOTEL POR TAMAÑO Y PRECIO NO REQUIERAN NINGUN AUTENTIFICACION
                    .antMatchers(HttpMethod.GET, "/api/hotel/localidad/{localidad}").permitAll()
                    .antMatchers(HttpMethod.GET, "/api/hotel/categoria/{categoria}").permitAll()
                    .antMatchers(HttpMethod.GET, "/api/habitacion/habitacionesLibres/{idHotel}/{tamanioMin}/{tamanioMax}/{precioMin}/{precioMax}").permitAll()
                    .antMatchers(HttpMethod.POST, "/api/loginUser").permitAll()

                    // PERMITE TODAS LAS RUTAS DEFINIDAS EN AUTH_WHITELIST (SWAGGER UI)
                    .antMatchers(AUTH_WHITELIST).permitAll()

                    // CUALQUIER SOLICITUD DEBE SER AUTENT  ICADA, DE LO CONTRARIO DEVOLVERA UNA RESPUESTA 401
                    .anyRequest().authenticated();
        }
    }
}
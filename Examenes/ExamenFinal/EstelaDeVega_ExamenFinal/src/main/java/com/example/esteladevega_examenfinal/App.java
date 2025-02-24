package com.example.esteladevega_examenfinal;

import com.example.esteladevega_examenfinal.security.JWTAuthorizationFilter;
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
                    // NO REQUIERAN NINGUN AUTENTIFICACION
                    .antMatchers(HttpMethod.POST, "/api/loginUser").permitAll()
                    .antMatchers(HttpMethod.GET, "/api/pisos/buscar-mayor-100-metros-y-no-reservado").permitAll()
                    .antMatchers(HttpMethod.GET, "/api/pisos/buscar-alquiler-menor-500-no-reservado").permitAll()
                    .antMatchers(HttpMethod.DELETE, "/api/pisos/delete/{idPiso}").permitAll()

                    // PERMITE TODAS LAS RUTAS DEFINIDAS EN AUTH_WHITELIST (SWAGGER UI)
                    .antMatchers(AUTH_WHITELIST).permitAll()

                    // CUALQUIER SOLICITUD DEBE SER AUTENTICADA, DE LO CONTRARIO DEVOLVERA UNA RESPUESTA 401
                    .anyRequest().authenticated();
        }
    }
}

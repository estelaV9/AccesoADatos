package com.example.esteladevega_examen2eva.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// SE USA JavaInUseSecurityScreme PARA PODER PONER EL TOKEN DIRECTAMENTE DESDE SWAGGER
@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components().addSecuritySchemes("JavaInUseSecurityScreme", new SecurityScheme().name("JavaInUseSecurityScreme").type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")))
                .addSecurityItem(new SecurityRequirement().addList("JavaInUseSecurityScreme"))
                .info(new Info().title("Examen2eva")
                        .description("Estela de Vega Examen 2 evaluación")
                        .contact(new Contact().name("Estela").email("esteladevega.dev9@gmail.com").url("https://github.com/estelaV9"))
                        .version("1.0"));
    }
}

package dev.tuxbe.aemiage.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI openApi() {
        return new OpenAPI().info(
                new Info()
                        .contact(new Contact().name("Beugré Toussaint").email("djire.toussaint@gmail.com"))
                        .title("Manage Account API Documentation").version("1.0")
                        .description("La mini application de gestion de compte a été mise en place dans le cadre d'un atelier de formation organiser par l'AEMIAGE ")
        );
    }
}

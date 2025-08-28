package com.ecommerce.project.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        SecurityScheme bearerScheme = new SecurityScheme()
                .type(SecurityScheme.Type.HTTP)
                .scheme("bearer")
                .bearerFormat("JWT")
                .description("JWT Bearer Token");

        SecurityRequirement bearerRequirement = new SecurityRequirement()
                .addList("Bearer Authentication");

        return new OpenAPI()
                .info(new Info()
                        .title("Spring Boot E-commerce API")
                        .version("1.0")
                        .description("E-commerce backend built with Spring Boot featuring layered architecture PostgreSQL, role-based JWT authentication & OAuth2, multiple entity relationships, global exception handling, pagination & sorting.\n" +
                                    "Includes email order confirmations, payment gateway integration, Swagger API docs, comprehensive testing, Dockerized deployment, and CI/CD-ready setup.")
                        .contact(new Contact()
                                .name("Roopak Dureja")
                                .email("roopakdureja08@gmail.com")
                                .url("https://www.linkedin.com/in/roopak-dureja/"))
                )
                .components(new Components()
                        .addSecuritySchemes("Bearer Authentication", bearerScheme))
                .addSecurityItem(bearerRequirement);
    }
}

package com.api.cliente.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ConfigCORS {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {

            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") // Aplica a todos os endpoints
                        .allowedOrigins("*") // Permite todas as origens
                        .allowedMethods("*") // Métodos permitidos
                        .allowedHeaders("*") // Permite todos os headers
                        .allowCredentials(false); // Se for true, allowedOrigins não pode ser "*"
            }
        };
    }
}
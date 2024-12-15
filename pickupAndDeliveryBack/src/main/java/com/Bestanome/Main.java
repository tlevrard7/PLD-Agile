package com.Bestanome;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.Bestanome.Model.Data;

@SpringBootApplication
@Configuration
@EnableWebMvc
public class Main implements WebMvcConfigurer {
    public static void main(String[] args) {
        Data.initialiserLivreurs(); // Initialiser les livreurs au démarrage
        SpringApplication.run(Main.class, args);
    }

    @Override
    public void addCorsMappings(@NonNull CorsRegistry registry) {
        registry.addMapping("/**");
    }
}

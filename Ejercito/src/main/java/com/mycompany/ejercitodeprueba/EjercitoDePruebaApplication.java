package com.mycompany.ejercitodeprueba;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// Esta anotación hace toda la magia:
// 1. Configura Spring
// 2. Escanea tus otras clases (como los futuros Controllers)
// 3. Inicia el servidor web
@SpringBootApplication
public class EjercitoDePruebaApplication {

    public static void main(String[] args) {
        // Esto ya no usa Scanner. Esto levanta el servidor
        // y lo deja corriendo para siempre.
        SpringApplication.run(EjercitoDePruebaApplication.class, args);

        System.out.println("--- EL SERVIDOR ESTÁ CORRIENDO ---");
        System.out.println("Accede a la API en http://localhost:8080");
    }
}
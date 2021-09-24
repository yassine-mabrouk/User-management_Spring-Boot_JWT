package com.enset.fbc.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyAppConfig  implements WebMvcConfigurer {

    public void addCorsMappings(CorsRegistry registry) {
        // Spesific
        /*
        registry.addMapping("/users")  // autoriser les path user
                .allowedMethods("PUT","POST","GET")// autoriser seulemet ses request
                .allowedOrigins("http://localhost:4200");// autoriser seulemt ce domaine
       }
         */
        registry.addMapping("/**")  // autoriser tout les path
                .allowedMethods("*")// autoriser tout les requestes
                .allowedOrigins("*");// tout les domaine
    }
    
}

package com.vibestechsolution.cabinetavocat.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {
    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOriginPattern("*"); // Permettre toutes les origines, vous pouvez restreindre cela si nécessaire
        config.addAllowedHeader("*"); // Permettre tous les en-têtes
        config.addAllowedMethod("*"); // Permettre toutes les méthodes HTTP
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}

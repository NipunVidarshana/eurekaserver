package com.logic.springjwt;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

@Configuration
public class CorsGlobalConfiguration {

    @Bean
    public CorsWebFilter corsWebFilter() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");

        config.addAllowedOriginPattern("https://itmd.treasury.gov.lk"); // Add multiple allowed origin patterns
        config.addAllowedOriginPattern("https://systems.treasury.gov.lk"); // Add multiple allowed origin patterns
        config.addAllowedOriginPattern("http://itmd.treasury.gov.lk"); // Add multiple allowed origin patterns
        config.addAllowedOriginPattern("http://systems.treasury.gov.lk"); // Add multiple allowed origin patterns
        config.addAllowedOriginPattern("http://localhost"); // Add multiple allowed origin patterns

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return new CorsWebFilter(source);
    }
}
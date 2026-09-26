package com.logic.springjwt.config.cors;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

@Configuration
@Profile("testing")
public class TestingCorsConfiguration {

    @Bean
    public CorsWebFilter corsWebFilter() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.setAllowedHeaders(List.of("*"));
        config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH", "HEAD"));
        config.setExposedHeaders(List.of("*"));
        config.setMaxAge(3600L);

        config.addAllowedOriginPattern("http://192.168.231.50*");
        config.addAllowedOriginPattern("https://192.168.231.50*");
        config.addAllowedOriginPattern("http://192.168.231.62*");
        config.addAllowedOriginPattern("https://192.168.231.62*");
        config.addAllowedOriginPattern("http://localhost*");
        config.addAllowedOriginPattern("https://localhost*");
        config.addAllowedOriginPattern("http://127.0.0.1*");
        config.addAllowedOriginPattern("https://127.0.0.1*");

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        return new CorsWebFilter(source);
    }
}

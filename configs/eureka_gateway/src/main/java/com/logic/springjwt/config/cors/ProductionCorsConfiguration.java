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
@Profile("production")
public class ProductionCorsConfiguration {

    @Bean
    public CorsWebFilter corsWebFilter() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.setMaxAge(3600L); // 1 hour preflight cache for high performance

        config.setAllowedHeaders(List.of("*"));
        config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS", "HEAD"));
        config.setExposedHeaders(Arrays.asList("Authorization", "Link", "X-Total-Count", "Content-Disposition"));

        config.addAllowedOriginPattern("https://itmd.treasury.gov.lk");
        config.addAllowedOriginPattern("https://systems.treasury.gov.lk");
        config.addAllowedOriginPattern("http://itmd.treasury.gov.lk");
        config.addAllowedOriginPattern("http://systems.treasury.gov.lk");
        config.addAllowedOriginPattern("http://192.168.250.96");

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        return new CorsWebFilter(source);
    }
}

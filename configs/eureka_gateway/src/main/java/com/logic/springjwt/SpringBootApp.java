package com.logic.springjwt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootVersion;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
@EnableDiscoveryClient
public class SpringBootApp {

    private static final Logger log = LoggerFactory.getLogger(SpringBootApp.class);

    public static void main(String[] args) {
        log.info("Starting Cloud Gateway Application");
        log.info("======================================================");
        log.info("Spring Boot Version: {}", SpringBootVersion.getVersion());
        log.info("Java Version: {}", System.getProperty("java.version"));
        log.info("======================================================");
        SpringApplication.run(SpringBootApp.class, args);
    }

    /**
     * Non-blocking reactive WebClient bean for downstream HTTP communication
     */
    @Bean
    public WebClient.Builder webClientBuilder() {
        return WebClient.builder();
    }
}

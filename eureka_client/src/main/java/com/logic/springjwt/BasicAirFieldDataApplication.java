package com.logic.springjwt;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootVersion;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextListener;

@SpringBootApplication
public class BasicAirFieldDataApplication {

    private static final Logger slsiLogger = LogManager.getLogger(BasicAirFieldDataApplication.class);

    @Autowired
    private MessageSource messageSource;

    public static void main(String[] args) {
        slsiLogger.info("Starting application");
        System.out.println("######################################################");
        System.out.println(" ");
        System.out.println("Spring boot version is " + SpringBootVersion.getVersion());
        SpringApplication app = new SpringApplication(BasicAirFieldDataApplication.class);
        app.run(args);
    }

    @Bean
    public RequestContextListener requestContextListener() {
        return new RequestContextListener();
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
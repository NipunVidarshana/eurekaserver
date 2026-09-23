package com.logic.springjwt.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.time.Instant;
import java.util.Map;

import org.springframework.http.MediaType;

@RestController
public class GatewayHealthController {

    private final Instant startTime = Instant.now();

    @GetMapping({"/health", "/gateway/health"})
    public Mono<ResponseEntity<Map<String, Object>>> health() {
        return Mono.just(ResponseEntity.ok(Map.of(
                "status", "UP",
                "service", "cloudgateway",
                "startedAt", startTime.toString(),
                "timestamp", Instant.now().toString()
        )));
    }

    @GetMapping(value = {"/info", "/gateway/info", "/actuator/info"}, produces = MediaType.TEXT_PLAIN_VALUE)
    public Mono<String> info() {
        return Mono.just("Test endpoint is working!");
    }
}

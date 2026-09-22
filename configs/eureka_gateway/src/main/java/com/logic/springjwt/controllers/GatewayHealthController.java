package com.logic.springjwt.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.time.Instant;
import java.util.Map;

@RestController
@RequestMapping("/gateway")
public class GatewayHealthController {

    private final Instant startTime = Instant.now();

    @GetMapping("/health")
    public Mono<ResponseEntity<Map<String, Object>>> health() {
        return Mono.just(ResponseEntity.ok(Map.of(
                "status", "UP",
                "service", "cloudgateway",
                "startedAt", startTime.toString(),
                "timestamp", Instant.now().toString()
        )));
    }

    @GetMapping("/info")
    public Mono<ResponseEntity<Map<String, String>>> info() {
        return Mono.just(ResponseEntity.ok(Map.of(
                "app", "ITMD Cloud Gateway",
                "status", "active"
        )));
    }
}

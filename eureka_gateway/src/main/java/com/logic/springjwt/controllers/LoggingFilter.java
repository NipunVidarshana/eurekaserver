package com.logic.springjwt.controllers;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import java.net.URI;

@Component
public class LoggingFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        System.out.println("Incoming request URI: " + exchange.getRequest().getURI());
        return chain.filter(exchange).then(Mono.fromRunnable(() -> {
            Route route = exchange.getAttribute(ServerWebExchangeUtils.GATEWAY_ROUTE_ATTR);
            URI routeUri = route.getUri();

            if (routeUri != null) {
                System.out.println("Routed to URI: " + routeUri);
            } else {
                System.out.println("Routed URI not found in exchange attributes.");
            }
            System.out.println("-------------------------------------------------------------------------------");
            System.out.println(" ");
        }));

    }

    @Override
    public int getOrder() {
        return -1; // Run early in the filter chain
    }
}
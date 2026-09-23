package com.logic.springjwt.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.URI;

@Component
public class LoggingFilter implements GlobalFilter, Ordered {

    private static final Logger log = LoggerFactory.getLogger(LoggingFilter.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        long startTime = System.currentTimeMillis();
        String path = exchange.getRequest().getURI().getRawPath();
        String method = exchange.getRequest().getMethod().name();

        if (log.isDebugEnabled()) {
            log.debug("Incoming Request: [{}] {}", method, path);
        }

        return chain.filter(exchange).then(Mono.fromRunnable(() -> {
            long duration = System.currentTimeMillis() - startTime;
            Route route = exchange.getAttribute(ServerWebExchangeUtils.GATEWAY_ROUTE_ATTR);
            URI routeUri = route != null ? route.getUri() : null;
            String routeId = route != null ? route.getId() : "unknown";
            HttpStatusCode statusCode = exchange.getResponse().getStatusCode();

            if (log.isDebugEnabled()) {
                log.debug("Request [{}] {} -> Routed to: [Route: {}, Target: {}] | Status: {} | Duration: {}ms",
                        method, path, routeId, routeUri, statusCode, duration);
            } else if (log.isInfoEnabled()) {
                log.info("Request: [{}] {} -> Route: [{}] | Status: {} | Latency: {}ms",
                        method, path, routeId, statusCode, duration);
            }
        }));
    }

    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE;
    }
}

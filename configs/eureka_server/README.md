# Eureka Service Discovery Server

Production-ready Spring Cloud Eureka Server upgraded to **Spring Boot 4.0.8**, **Spring Cloud 2025.1.2 (Oakwood)**, and **Java 25 LTS (Temurin-25)**.

---

## 🚀 Key Improvements & Upgrades

1. **Modern Runtime**: Upgraded to Java 25 LTS baseline with latest Spring Boot 4.0.8 and Spring Cloud 2025.1.2 BOM dependencies.
2. **Network Resilience & Recovery**:
   - Eliminated standalone 5-minute startup sync delay (`eureka.server.wait-time-in-ms-when-sync-empty: 0`).
   - Reduced response cache latency from 30s to 3s (`eureka.server.response-cache-update-interval-ms: 3000`) for near-instant visibility upon instance re-registration.
   - Dynamic threshold recalibration (`renewal-threshold-update-interval-ms: 300000`) so network drops do not leave stale heartbeat thresholds in Eureka.
   - Self-preservation mode (`enable-self-preservation: true`) in production to prevent false mass-evictions during transient network blips.
3. **Production Performance**:
   - Tomcat thread pool tuning (`max: 200`, `min-spare: 20`, `max-connections: 10000`).
   - GZIP response compression for JSON/XML payloads.
   - Graceful shutdown (`server.shutdown: graceful`, 30s timeout).
   - Actuator endpoints exposed (`/actuator/health`, `/actuator/info`, `/actuator/prometheus`, `/actuator/metrics`).

---

## 🛠️ Configuration Profiles

| Profile | Command / Setting | Purpose |
| :--- | :--- | :--- |
| **development** | `spring.profiles.active=development` (Default) | Fast 5s evictions and 1s cache updates for local development. |
| **production** | `spring.profiles.active=production` | High-throughput Tomcat pool, compression, self-preservation enabled, 60s eviction sweeps. |
| **testing** | `spring.profiles.active=testing` | Fast evictions for CI/CD test suites. |

---

## 📡 Recommended Client VM Configuration

To ensure your microservices running on separate VMs automatically re-register without delay when network connectivity restores after a brief cutoff, configure their `application.yaml` / `application.properties` with:

```yaml
eureka:
  instance:
    # Prefer IP addresses across distinct VM subnets
    prefer-ip-address: true
    # Send heartbeats every 10 seconds (default is 30s)
    lease-renewal-interval-in-seconds: 10
    # Expire lease after 30 seconds of missed heartbeats (default is 90s)
    lease-expiration-duration-in-seconds: 30
  client:
    service-url:
      defaultZone: http://<eureka-server-host>:8761/eureka/
    # Refresh local service registry cache every 10s
    registry-fetch-interval-seconds: 10
    # Enable Spring Boot health check handler for Eureka registration status
    healthcheck:
      enabled: true
    # Exponential retry backoff on registration failure
    initial-instance-info-replication-interval-seconds: 5
    instance-info-replication-interval-seconds: 10
```

### Why this fixes the client VM recovery issue:
- If network cuts off briefly and the Eureka Server evicts or self-preserves, when network comes back, the client VM sends a heartbeat renewal (`PUT`).
- If Eureka server returned `404 Not Found` (due to eviction), Eureka client detects the 404 and initiates a fresh registration (`POST /eureka/apps/...`).
- With `response-cache-update-interval-ms: 3000` on the server and `lease-renewal-interval-in-seconds: 10` on the client, the instance is restored in the registry within ~3-10 seconds!

---

## 📊 Endpoints

- **Eureka Web Dashboard**: [http://localhost:8761/](http://localhost:8761/)
- **Eureka REST Apps**: [http://localhost:8761/eureka/apps](http://localhost:8761/eureka/apps)
- **Health Check**: [http://localhost:8761/actuator/health](http://localhost:8761/actuator/health)
- **Prometheus Metrics**: [http://localhost:8761/actuator/prometheus](http://localhost:8761/actuator/prometheus)

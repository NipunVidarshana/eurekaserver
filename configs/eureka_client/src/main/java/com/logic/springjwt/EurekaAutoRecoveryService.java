package com.logic.springjwt;

import com.netflix.appinfo.ApplicationInfoManager;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicBoolean;

@Component
@ConditionalOnBean(EurekaClient.class)
public class EurekaAutoRecoveryService {

    private static final Logger log = LoggerFactory.getLogger(EurekaAutoRecoveryService.class);

    private final EurekaClient eurekaClient;
    private final ApplicationInfoManager applicationInfoManager;

    @Value("${eureka.client.register-with-eureka:true}")
    private boolean registerWithEureka;

    private final AtomicBoolean wasDisconnected = new AtomicBoolean(false);

    @Autowired(required = false)
    public EurekaAutoRecoveryService(EurekaClient eurekaClient, ApplicationInfoManager applicationInfoManager) {
        this.eurekaClient = eurekaClient;
        this.applicationInfoManager = applicationInfoManager;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void onApplicationReady() {
        if (registerWithEureka && applicationInfoManager != null) {
            log.info("Initializing Eureka Auto-Recovery Service. Instance ID: {}",
                    applicationInfoManager.getInfo().getInstanceId());
        }
    }

    /**
     * Periodic check to verify Eureka connectivity and trigger self-healing re-registration
     * after network disconnection.
     */
    @Scheduled(fixedDelayString = "${eureka.client.recovery-check-interval-ms:15000}", initialDelay = 10000)
    public void checkAndRecoverRegistration() {
        if (!registerWithEureka || eurekaClient == null || applicationInfoManager == null) {
            return;
        }

        try {
            // Attempt to retrieve applications from Eureka server
            int appCount = eurekaClient.getApplications().getRegisteredApplications().size();
            
            if (wasDisconnected.getAndSet(false)) {
                log.info("Network restored! Re-registering with Eureka server (discovered {} apps)...", appCount);
                applicationInfoManager.setInstanceStatus(InstanceInfo.InstanceStatus.UP);
            }
        } catch (Exception e) {
            wasDisconnected.set(true);
            log.warn("Network disconnect / Eureka unreachable: {}. Will automatically re-register upon reconnection.", e.getMessage());
        }
    }
}

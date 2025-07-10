package com.potodev.NapRoute.scheduledJobs;

import com.potodev.NapRoute.configs.NapServiceConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
@RequiredArgsConstructor
public class NapHealthScheduler {

    private final NapServiceConfig config;
    private final RestTemplate restTemplate;

    private static final String HEALTH_CHECK_URL = "https://naproute.onrender.com/health";

    @Scheduled(fixedDelayString = "#{@napServiceConfig.pollTime}")
    public void checkNapServiceHealth() {
        try {
            ResponseEntity<String> response = restTemplate.getForEntity(HEALTH_CHECK_URL, String.class);
            log.info("NapRoute Health Check: Status - {}, Body - {}", response.getStatusCode(), response.getBody());
        } catch (Exception e) {
            log.error("NapRoute Health Check failed", e);
        }
    }
}


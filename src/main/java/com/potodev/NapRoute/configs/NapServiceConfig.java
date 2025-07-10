package com.potodev.NapRoute.configs;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "nap.service")
@Data
public class NapServiceConfig {
    private long pollTime;
}


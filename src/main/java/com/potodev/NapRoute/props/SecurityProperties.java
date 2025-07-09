package com.potodev.NapRoute.props;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "security")
public class SecurityProperties {

    private List<String> whitelistedUrls = new ArrayList<>();
    private List<String> filterUrls = new ArrayList<>();
}
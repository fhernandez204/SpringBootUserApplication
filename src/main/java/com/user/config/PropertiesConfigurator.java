package com.user.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
@ConfigurationProperties(prefix="ss")
public class PropertiesConfigurator {
    private String username;
    private String password;
    private String username_;
    private String password_;
}

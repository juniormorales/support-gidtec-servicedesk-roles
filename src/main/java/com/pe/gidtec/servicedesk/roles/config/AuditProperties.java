package com.pe.gidtec.servicedesk.roles.config;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
@ConfigurationProperties(prefix = "audit-properties")
@Getter
@Setter
@Builder
public class AuditProperties {
    private Map<String, String> statusCode;
}

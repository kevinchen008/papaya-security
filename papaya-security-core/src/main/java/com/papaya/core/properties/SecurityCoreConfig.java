package com.papaya.core.properties;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(PapayaSecurityProperties.class)
public class SecurityCoreConfig {
}

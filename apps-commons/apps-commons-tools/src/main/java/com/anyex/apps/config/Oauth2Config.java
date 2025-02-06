package com.anyex.apps.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "com.anyex.oauth2")
public class Oauth2Config
{
    private String clientId;
    
    private String clientSecret;
    
    private String redirectUrl;
    
    private String userInfoUrl;
    
    private String accessTokenUrl;
    
    private String authorizeUrl;
    
    private String successUrl;
    
    private String failureUrl;
    
    private String accessLogoutUrl;
}

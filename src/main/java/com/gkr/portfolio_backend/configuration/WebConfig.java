package com.gkr.portfolio_backend.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private Environment environment;
    private String[] allowedOrigins; // Read the origins array dynamically from your active yml file

    public WebConfig(Environment environment,
                     @Value("${app.cors.allowed-origins:http://localhost:8081}") String[] allowedOrigins) {
        this.environment = environment;
        this.allowedOrigins = allowedOrigins;
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {

//        Arrays.stream(environment.getActiveProfiles()).toList().forEach(System.out::println);
//        Arrays.stream(environment.getDefaultProfiles()).toList().forEach(System.out::println);

        registry.addMapping("/**")
                .allowedOrigins(allowedOrigins) // Injects your specific whitelisted list dynamically
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .exposedHeaders("Content-Type", "Content-Disposition")
                .allowCredentials(true) // Crucial for security handshakes across distinct tracking domains
                .maxAge(3600); // Caches the pre-flight handshake options for 1 hour to increase speed
    }
}
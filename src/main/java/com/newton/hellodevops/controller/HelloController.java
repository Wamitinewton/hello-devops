package com.newton.hellodevops.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class HelloController {

    @Value("${app.version:1.0.0}")
    private String version;

    @Value("${app.environment:local}")
    private String environment;

    @GetMapping("/")
    public Map<String, String> helloDevops() {
        return Map.of(
                "message", "Hello DevOps!",
                "version", version,
                "environment", environment,
                "status", "Running"
        );
    }

    @GetMapping("/health")
    public Map<String, String> health() {
        return Map.of("status", "UP");
    }
}
package com.shopify.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {
    
    @GetMapping(path = "/auth/health")
    public String healthCheck() {
        return "Server is running correctly";
    }
}

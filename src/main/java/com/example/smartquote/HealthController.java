package com.example.smartquote;

// === 2️⃣ Add a /health endpoint ===
// This endpoint will return the app status and version info (from ENV variable) to help with DR tests.

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/health")
public class HealthController {
    private static final Logger logger = LoggerFactory.getLogger(HealthController.class);
    private final String version = System.getenv().getOrDefault("APP_VERSION", "v1.0-unknown");

    @GetMapping
    public Map<String, String> health() {
        logger.info("[HEALTH] Health check called, version {}", version);
        Map<String, String> status = new HashMap<>();
        status.put("status", "UP");
        status.put("version", version);
        return status;
    }
}
package com.example.smartquote;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/simulate-error")
class ErrorController {
    private static final Logger logger = LoggerFactory.getLogger(ErrorController.class);

    @GetMapping
    public String simulateError() {
        logger.error("[SIMULATE_ERROR] Intentional error triggered for rollback test");
        throw new RuntimeException("Simulated deployment failure for rollback testing");
    }
}
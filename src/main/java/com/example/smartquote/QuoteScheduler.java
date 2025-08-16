package com.example.smartquote;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
class QuoteScheduler {
    @Autowired
    private QuoteService quoteService;

    // Runs once every 24 hours (example: 9 AM UTC)
    @Scheduled(cron = "0 0 9 * * ?")
    public void sendDailyDigest() {
        System.out.println("[Scheduled Job] Daily Digest: " + String.join(" | ", quoteService.getAllQuotes()));
    }
}
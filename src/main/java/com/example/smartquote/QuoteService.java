package com.example.smartquote;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
class QuoteService {
    private static final Logger logger = LoggerFactory.getLogger(QuoteService.class);
    private final List<String> quotes = new CopyOnWriteArrayList<>(Arrays.asList(
            "Innovation distinguishes between a leader and a follower.",
            "Simplicity is the ultimate sophistication.",
            "Code is like humor. When you have to explain it, it’s bad."
    ));

    @PostConstruct
    public void onStartup() {
        logger.info("[COLD_START] SmartQuote application started at {}", new Date());
        // Simulate secret fetch from ENV or config service
        String dbSecret = System.getenv().getOrDefault("DB_CONNECTION", "dummy-secret");
        logger.info("[SECRET_FETCH] Loaded DB connection secret: {}", dbSecret.substring(0, Math.min(5, dbSecret.length())) + "***");
    }

    public List<String> getAllQuotes() {
        return quotes;
    }

    public String getRandomQuote() {
        return quotes.get(new Random().nextInt(quotes.size()));
    }

    public String addQuote(String quote) {
        quotes.add(quote);
        logger.info("[EVENT] New quote added, pushing to Azure Queue: {}", quote);
        return "Quote added successfully.";
    }
}

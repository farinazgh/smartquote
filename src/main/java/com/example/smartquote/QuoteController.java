package com.example.smartquote;

// === 1️⃣ Add Performance Logging ===
// We'll use Spring's @Around advice or basic timestamp logging in each controller method.
// Logs will include request start/end times and latency.

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quotes")
public class QuoteController {

    private static final Logger logger = LoggerFactory.getLogger(QuoteController.class);
    @Autowired
    private QuoteService quoteService;

    @GetMapping
    public List<String> getAllQuotes() {
        long start = System.currentTimeMillis();
        List<String> quotes = quoteService.getAllQuotes();
        long duration = System.currentTimeMillis() - start;
        logger.info("[PERF] GET /quotes processed in {} ms", duration);
        return quotes;
    }

    @GetMapping("/random")
    public String getRandomQuote() {
        long start = System.currentTimeMillis();
        String quote = quoteService.getRandomQuote();
        long duration = System.currentTimeMillis() - start;
        logger.info("[PERF] GET /quotes/random processed in {} ms", duration);
        return quote;
    }

    @PostMapping
    public String addQuote(@RequestBody String quote) {
        long start = System.currentTimeMillis();
        String result = quoteService.addQuote(quote);
        long duration = System.currentTimeMillis() - start;
        logger.info("[PERF] POST /quotes processed in {} ms", duration);
        return result;
    }
}

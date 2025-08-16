package com.example.smartquote;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AuthorController {

    @GetMapping("/authors")
    public List<String> getAuthors() {
        return List.of("Edsger Dijkstra", "Linus Torvalds", "Ada Lovelace");
    }
}

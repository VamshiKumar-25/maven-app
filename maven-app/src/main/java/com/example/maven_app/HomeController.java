package com.example.my_maven_app;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "Spring Boot Maven App CI/CD using Jenkins + Docker Swarm 🚀";
    }
}


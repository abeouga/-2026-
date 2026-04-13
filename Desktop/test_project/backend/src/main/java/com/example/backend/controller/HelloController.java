package com.example.backend.controller;

import com.example.backend.model.HelloResponse;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5173") // Default Vite port
public class HelloController {

    @GetMapping("/hello")
    public HelloResponse getHello() {
        // MVC: Controller returns a Model (HelloResponse). 
        // Jackson converts this Model to JSON (View).
        return new HelloResponse("Hello world");
    }
}

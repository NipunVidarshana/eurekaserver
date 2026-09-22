package com.logic.springjwt.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping
public class helthChecker {

    @GetMapping(path = {"/health", "/client/info"})
    public Map<String, Object> healthInfo() {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "UP");
        response.put("message", "Client is healthy and working!");
        return response;
    }

    @GetMapping("/actuator/custom-info")
    public String testEndpoint() {
        return "Test endpoint is working!";
    }
}

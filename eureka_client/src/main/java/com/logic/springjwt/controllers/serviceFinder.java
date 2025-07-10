package com.logic.springjwt.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping
public class serviceFinder {

    @Value("${spring.application.name}")
    private String serviceName;

    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/find/service/status")
    public String testEndpoint() {
        return "Service is working on ---> "+serviceName + " --->  port :"+ serverPort;
    }

    @GetMapping("/getdetails")
    public String testEndpointFind() {
        return "Detail one";
    }

    @GetMapping("/getdetailsTwo")
    public String testEndpointFindTwo() {
        return "Detail two";
    }

    @GetMapping("/getdetailsThree")
    public String testEndpointFindThree() {
        return "Detail three";
    }

    @GetMapping("/getdetailsFour")
    public String testEndpointFindFour() {
        return "Detail four";
    }

    @GetMapping("/getdetailsFive")
    public String testEndpointFindFive() {
        return "Detail five";
    }
}

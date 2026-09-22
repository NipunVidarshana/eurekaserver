package com.logic.springjwt.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping
public class serviceFinder {

    @Value("${spring.application.name:TEST-SYSETEM}")
    private String serviceName;

    @Value("${server.port:9095}")
    private String serverPort;

    @GetMapping("/find/service/status")
    public Map<String, String> getServiceStatus() {
        Map<String, String> status = new HashMap<>();
        status.put("serviceName", serviceName);
        status.put("serverPort", serverPort);
        status.put("message", "Service is working on ---> " + serviceName + " ---> port :" + serverPort);
        return status;
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

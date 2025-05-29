package com.dod.devopsdashboardbackend.controller;

import com.dod.devopsdashboardbackend.service.SystemAvailabilityMetricsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/system-availability")
public class SystemAvailabilityMetricsController {

    @Autowired
    private SystemAvailabilityMetricsService service;

    @GetMapping("/{serviceName}")
    public ResponseEntity<Double> getAvailabilityByService(@PathVariable String serviceName) {
        return ResponseEntity.ok(service.calculateAvailabilityForService(serviceName));
    }

    @GetMapping("/all")
    public ResponseEntity<Map<String, Double>> getAllAvailabilities() {
        return ResponseEntity.ok(service.getAllServiceAvailabilities());
    }
}

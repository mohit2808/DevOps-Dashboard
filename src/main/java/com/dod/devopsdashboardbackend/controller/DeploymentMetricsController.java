package com.dod.devopsdashboardbackend.controller;

import com.dod.devopsdashboardbackend.model.DeploymentMetrics;
import com.dod.devopsdashboardbackend.service.DeploymentMetricsService;
import lombok.NoArgsConstructor;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/deployments")
public class DeploymentMetricsController {

    @Autowired
    private DeploymentMetricsService metricsService;

    @GetMapping
    public List<DeploymentMetrics> getAllDeployments() {
        return metricsService.getAllDeployments();
    }

    @PostMapping
    public DeploymentMetrics createDeployment(@RequestBody DeploymentMetrics deploymentMetrics) {
//        if (deploymentMetrics.getTimestamp() == null) {
//            deploymentMetrics.setTimestamp(LocalDateTime.now());
//        }

        return metricsService.saveDeployment(deploymentMetrics);
    }

    @GetMapping("/average-lead-time")
    public ResponseEntity<Double> getAverageLeadTime() {
        return ResponseEntity.ok(metricsService.getAverageLeadTimeInMinutes());
    }

    @GetMapping("/change-failure-rate")
    public ResponseEntity<Double> getChangeFailureRate() {
        return ResponseEntity.ok(metricsService.getChangeFailureRatePercentage());
    }

    @GetMapping("/mean-time-to-recover")
    public ResponseEntity<Double> getMeanTimeToRecover() {
        return ResponseEntity.ok(metricsService.getMeanTimeToRecoverInMinutes());
    }

    @GetMapping("/deployment-frequency")
    public ResponseEntity<Long> getDeploymentFrequency(
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {

        long frequency = metricsService.getDeploymentFrequency(startDate, endDate);
        return ResponseEntity.ok(frequency);
    }
}


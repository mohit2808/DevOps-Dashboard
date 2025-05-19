package com.dod.devopsdashboardbackend.controller;

import com.dod.devopsdashboardbackend.model.DeploymentMetrics;
import com.dod.devopsdashboardbackend.service.DeploymentMetricsService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
        if (deploymentMetrics.getTimestamp() == null) {
            deploymentMetrics.setTimestamp(LocalDateTime.now());
        }

        return metricsService.saveDeployment(deploymentMetrics);
    }
}


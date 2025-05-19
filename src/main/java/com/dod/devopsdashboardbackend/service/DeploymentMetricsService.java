package com.dod.devopsdashboardbackend.service;

import com.dod.devopsdashboardbackend.model.DeploymentMetrics;
import com.dod.devopsdashboardbackend.repository.DeploymentMetricsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeploymentMetricsService {

    @Autowired
    private DeploymentMetricsRepository metricsRepository;

    public List<DeploymentMetrics> getAllDeployments() {
        return metricsRepository.findAll();
    }

    public DeploymentMetrics saveDeployment(DeploymentMetrics deploymentMetrics) {
        return metricsRepository.save(deploymentMetrics);
    }
}

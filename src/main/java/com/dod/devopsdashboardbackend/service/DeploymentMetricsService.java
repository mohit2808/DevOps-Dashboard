package com.dod.devopsdashboardbackend.service;

import com.dod.devopsdashboardbackend.model.DeploymentMetrics;
import com.dod.devopsdashboardbackend.repository.DeploymentMetricsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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

    public Double getAverageLeadTimeInMinutes() {
        List<DeploymentMetrics> deployments = metricsRepository.findAll();

        List<Long> leadTimes = deployments.stream()
                .map(DeploymentMetrics::getLeadTimeMinutes)
                .filter(Objects::nonNull)
                .toList();

        if (leadTimes.isEmpty()) return 0.0;

        double avg = leadTimes.stream().mapToLong(Long::longValue).average().orElse(0.0);
        return Math.round(avg * 100.0) / 100.0; // rounded to 2 decimal places
    }

    public Double getChangeFailureRatePercentage() {
        List<DeploymentMetrics> deployments = metricsRepository.findAll();

        if (deployments.isEmpty()) return 0.0;

        long total = deployments.size();
        long failed = deployments.stream()
                .filter(d -> d.getStatus() != null && d.getStatus().equalsIgnoreCase("FAILURE"))
                .count();

        double rate = ((double) failed / total) * 100;
        return Math.round(rate * 100.0) / 100.0; // round to 2 decimal places
    }

    //We'll use this to calculate the time difference between a failed deployment and the next successful one.
    public Double getMeanTimeToRecoverInMinutes() {
        List<DeploymentMetrics> deployments = metricsRepository.findAll().stream()
                .filter(d -> d.getTimestamp() != null && d.getStatus() != null)
                .sorted(Comparator.comparing(DeploymentMetrics::getTimestamp))
                .collect(Collectors.toList());

        List<Long> recoveryTimes = new ArrayList<>();

        for (int i = 0; i < deployments.size(); i++) {
            DeploymentMetrics current = deployments.get(i);

            if (current.getStatus().equalsIgnoreCase("FAILURE")) {
                // Find the next successful deployment
                for (int j = i + 1; j < deployments.size(); j++) {
                    DeploymentMetrics next = deployments.get(j);

                    if (next.getStatus().equalsIgnoreCase("SUCCESS")) {
                        long duration = Duration.between(
                                current.getTimestamp(),
                                next.getTimestamp()
                        ).toMinutes();
                        recoveryTimes.add(duration);
                        break;
                    }
                }
            }
        }

        if (recoveryTimes.isEmpty()) return 0.0;

        double avg = recoveryTimes.stream().mapToLong(Long::longValue).average().orElse(0.0);
        return Math.round(avg * 100.0) / 100.0;
    }

    public long getDeploymentFrequency(LocalDate startDate, LocalDate endDate) {
        // Convert LocalDate to LocalDateTime for the start of the day
        LocalDateTime startDateTime = startDate.atStartOfDay();
        // Convert LocalDate to LocalDateTime for the end of the day (inclusive)
        LocalDateTime endDateTime = endDate.atTime(LocalTime.MAX); // Or endDate.plusDays(1).atStartOfDay() if you want exclusive end

        return metricsRepository.countSuccessfulDeploymentsBetween(startDateTime, endDateTime);
    }

}

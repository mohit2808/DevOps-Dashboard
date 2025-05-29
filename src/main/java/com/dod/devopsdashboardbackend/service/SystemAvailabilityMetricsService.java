package com.dod.devopsdashboardbackend.service;

import com.dod.devopsdashboardbackend.model.SystemAvailabilityMetrics;
import com.dod.devopsdashboardbackend.repository.SystemAvailabilityMetricsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class SystemAvailabilityMetricsService {

    @Autowired
    private SystemAvailabilityMetricsRepository repository;

    public double calculateAvailabilityForService(String serviceName) {
        List<SystemAvailabilityMetrics> logs = repository.findAllByServiceNameOrderByTimestampAsc(serviceName);
        if (logs.size() < 2) return 100.0;

        long uptime = 0;
        long downtime = 0;

        for (int i = 0; i < logs.size() - 1; i++) {
            SystemAvailabilityMetrics current = logs.get(i);
            SystemAvailabilityMetrics next = logs.get(i + 1);

            long duration = Duration.between(
                    current.getTimestamp().toInstant(),
                    next.getTimestamp().toInstant()
            ).toMinutes();

            if ("UP".equalsIgnoreCase(current.getStatus())) {
                uptime += duration;
            } else if ("DOWN".equalsIgnoreCase(current.getStatus())) {
                downtime += duration;
            }
        }

        long total = uptime + downtime;
        if (total == 0) return 100.0;

        double availability = ((double) uptime / total) * 100;
        return Math.round(availability * 100.0) / 100.0;
    }

    public Map<String, Double> getAllServiceAvailabilities() {
        List<String> serviceNames = repository.findAll()
                .stream()
                .map(SystemAvailabilityMetrics::getServiceName)
                .filter(Objects::nonNull)
                .distinct()
                .toList();

        Map<String, Double> results = new HashMap<>();
        for (String name : serviceNames) {
            results.put(name, calculateAvailabilityForService(name));
        }
        return results;
    }

}

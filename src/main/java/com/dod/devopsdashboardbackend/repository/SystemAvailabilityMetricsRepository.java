package com.dod.devopsdashboardbackend.repository;

import com.dod.devopsdashboardbackend.model.SystemAvailabilityMetrics;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SystemAvailabilityMetricsRepository extends JpaRepository<SystemAvailabilityMetrics, Long> {
    List<SystemAvailabilityMetrics> findAllByServiceNameOrderByTimestampAsc(String serviceName);
}

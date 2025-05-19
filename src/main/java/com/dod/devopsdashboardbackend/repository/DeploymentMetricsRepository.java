package com.dod.devopsdashboardbackend.repository;


import com.dod.devopsdashboardbackend.model.DeploymentMetrics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeploymentMetricsRepository extends JpaRepository<DeploymentMetrics, Long> {
}


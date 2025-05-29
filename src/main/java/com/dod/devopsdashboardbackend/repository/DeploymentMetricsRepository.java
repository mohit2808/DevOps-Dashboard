package com.dod.devopsdashboardbackend.repository;

import com.dod.devopsdashboardbackend.model.DeploymentMetrics;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;


@Repository
public interface DeploymentMetricsRepository extends JpaRepository<DeploymentMetrics, Long> {

    @Query("SELECT COUNT(d) FROM DeploymentMetrics d WHERE d.status = 'SUCCESS' AND d.timestamp >= :start AND d.timestamp <= :end")
    long countSuccessfulDeploymentsBetween(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end);
}

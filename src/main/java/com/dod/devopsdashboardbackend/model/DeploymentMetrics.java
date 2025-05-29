package com.dod.devopsdashboardbackend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "deployments")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeploymentMetrics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "job_name", nullable = false)
    private String jobName;

    @Column(name = "build_id", nullable = false)
    private String buildId;

    @Column(name = "status")
    private String status;

    @Column(name = "timestamp")
    private LocalDateTime timestamp;

    @Column(name = "code_commit_time")
    private LocalDateTime codeCommitTime;

    @Column(name = "deployment_time")
    private LocalDateTime deploymentTime;

    @Transient
    private Long leadTimeMinutes;

    public Long getLeadTimeMinutes() {
        if (codeCommitTime != null && deploymentTime != null) {
            return java.time.Duration.between(codeCommitTime, deploymentTime).toMinutes();
        }
        return null;
    }

}


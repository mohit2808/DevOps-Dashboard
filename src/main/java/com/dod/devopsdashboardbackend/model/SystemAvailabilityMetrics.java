package com.dod.devopsdashboardbackend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Table(name = "system_availability")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SystemAvailabilityMetrics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "service_name")
    private String serviceName;

    @Column(name = " status")
    private String status; // "UP" or "DOWN"

    @Column(name = "uptime_percentage")
    private BigDecimal uptimePercentage; // optional

    @Column(name = "timestamp")
    private Timestamp timestamp;
}

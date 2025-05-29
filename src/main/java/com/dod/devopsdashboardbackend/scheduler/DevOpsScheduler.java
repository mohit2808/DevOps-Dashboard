//package com.dod.devopsdashboardbackend.scheduler;
//
//import com.dod.devopsdashboardbackend.model.DeploymentMetrics;
//import com.dod.devopsdashboardbackend.service.DeploymentMetricsService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
//import java.time.LocalDateTime;
//
//@Component
//public class DevOpsScheduler {
//
//    @Autowired
//    private DeploymentMetricsService metricsService;
//
//    // Runs every 5 minutes
//    @Scheduled(fixedRate = 30000)
//    public void fetchJenkinsDeploymentMetrics() {
//        // Placeholder: Simulate fetching data from Jenkins
//        DeploymentMetrics mockData = new DeploymentMetrics();
//        mockData.setJobName("Scheduled Jenkins Job");
//        mockData.setBuildId("auto-101");
//        mockData.setStatus("SUCCESS");
//        mockData.setTimestamp(LocalDateTime.now());
//        mockData.setCodeCommitTime(LocalDateTime.of(2024, 5, 1, 10, 0));
//        mockData.setDeploymentTime(LocalDateTime.of(2024, 5, 1, 12, 0));
//
//        metricsService.saveDeployment(mockData);
//        System.out.println("✅ [Scheduler] Fetched and saved deployment metric at " + LocalDateTime.now());
//    }
//}
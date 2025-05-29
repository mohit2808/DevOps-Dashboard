package com.dod.devopsdashboardbackend.scheduler;

import com.dod.devopsdashboardbackend.model.DeploymentMetrics;
import com.dod.devopsdashboardbackend.repository.DeploymentMetricsRepository;
import com.dod.devopsdashboardbackend.service.JenkinsClientService;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class JenkinsPollingService {

    @Autowired
    private JenkinsClientService jenkinsClientService;

    @Autowired
    private DeploymentMetricsRepository repository;

    @Scheduled(fixedRate = 60000) // every 60 seconds
    public void pollJenkinsJob() {
        String jobName = "GithubWebhook";
        JsonNode buildInfo = jenkinsClientService.fetchJobDetails(jobName);

        if (buildInfo != null) {
            DeploymentMetrics metrics = new DeploymentMetrics();
            metrics.setJobName(jobName);
            metrics.setBuildId(buildInfo.path("id").asText());
            metrics.setStatus(buildInfo.path("result").asText());
            metrics.setTimestamp(new Timestamp(System.currentTimeMillis()).toLocalDateTime());

            repository.save(metrics);
        }
    }
}

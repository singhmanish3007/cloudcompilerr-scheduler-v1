package com.cloudcompilerr.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@ConfigurationProperties(prefix = "cloudcompilerr.scheduler")
@Data
public class SchedulerProps {

    private String cron;
    private String zone;

}

package com.cloudcompilerr.scheduler;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.cloudcompilerr.config.SchedulerProps;

import lombok.extern.slf4j.Slf4j;

@Component
@EnableScheduling
@Slf4j
@EnableConfigurationProperties(SchedulerProps.class)
public class CloudCompilerrScheduler {

    private final SchedulerProps schedulerProps;

    @Autowired
    public CloudCompilerrScheduler(SchedulerProps schedulerProps) {
	this.schedulerProps = schedulerProps;
    }

    @Scheduled(cron = "${cloudcompilerr.scheduler.cron}", zone = "${cloudcompilerr.scheduler.zone}")
    public void scheduleJob() {
	Logger.info("inside scheduled job at {}", Calendar.getInstance().getTime());
	Logger.info("time zone is {}", schedulerProps.getZone());
    }

}

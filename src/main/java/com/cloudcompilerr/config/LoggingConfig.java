package com.cloudcompilerr.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

@Configuration
public class LoggingConfig {

    @Bean
    public CommonsRequestLoggingFilter requestLoggingFilter() {
	CommonsRequestLoggingFilter loggingFilter = new CommonsRequestLoggingFilter();
	loggingFilter.setIncludeClientInfo(true);
	loggingFilter.setIncludeQueryString(true);
	loggingFilter.setIncludePayload(true);
	loggingFilter.setAfterMessagePrefix("REQUEST DATA : ");
	return loggingFilter;
    }

}

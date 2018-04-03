package com.cloudcompilerr.controller;

import org.jboss.logging.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cloudcompilerr.scheduler.CloudCompilerrScheduler;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class SampleRestController {

    private final CloudCompilerrScheduler cloudCompilerrScheduler;

    @Autowired
    public SampleRestController(CloudCompilerrScheduler cloudCompilerrScheduler) {
	this.cloudCompilerrScheduler = cloudCompilerrScheduler;
    }

    @ApiOperation(value = "invoke scheduler", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses({ @ApiResponse(code = 200, message = "Success"), @ApiResponse(code = 400, message = "Bad Message") })
    @GetMapping(value = "/calljob/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public String invokeScheduler(
	    @ApiParam(name = "id", defaultValue = "1", required = false) @PathVariable("id") int id) {
	cloudCompilerrScheduler.scheduleJob();
	MDC.put("Method_Name", "invokeScheduler");
	Logger.info("inside rest controller");
	MDC.getMap().entrySet().stream()
		.forEach(entry -> Logger.info("key {} , value {} from MDC map ", entry.getKey(), entry.getValue()));
	return "Triggered successfully";
    }

}

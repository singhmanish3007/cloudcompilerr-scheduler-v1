package com.cloudcompilerr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cloudcompilerr.annotation.Traceable;
import com.cloudcompilerr.controller.error.CloudCompilerrErrorResponse;
import com.cloudcompilerr.scheduler.CloudCompilerrScheduler;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Extension;
import io.swagger.annotations.ExtensionProperty;
import io.swagger.annotations.ResponseHeader;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class SampleRestController {

	private final CloudCompilerrScheduler cloudCompilerrScheduler;

	@Autowired
	public SampleRestController(
			CloudCompilerrScheduler cloudCompilerrScheduler) {
		this.cloudCompilerrScheduler = cloudCompilerrScheduler;
	}

	@ApiOperation(value = "Invoke Scheduler",

			extensions = @Extension(properties = {
					@ExtensionProperty(name = "x-incident_priority", value = "P2"),

					@ExtensionProperty(name = "x-response_time_sla", value = "3000ms"),

					@ExtensionProperty(name = "x-success_http_code", value = "200"),

					@ExtensionProperty(name = "x-expected_tps", value = "100")}))

	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Scheduler Invoked Successfully", responseHeaders = {

					@ResponseHeader(name = "Link", description = "Pagination link headers", response = String.class),

					@ResponseHeader(name = "X-Api-Id", description = "The identifier for this API", response = String.class),

					@ResponseHeader(name = "X-RateLimit-Limit",

							description = "The defined maximum number of requests available to the consumer for this API",

							response = String.class),

					@ResponseHeader(name = "X-RateLimit-Remaining",

							description = "The number of calls remaining before the limit is enforced and requests are bounced",

							response = Integer.class),

					@ResponseHeader(name = "X-RateLimit-Reset",

							description = "The time, in seconds, until the limit expires and another request will be allowed in. This header will only be present if the limit is being enforced",

							response = Integer.class)}),

			@ApiResponse(code = 400, message = "Bad Request", response = CloudCompilerrErrorResponse.class),

			@ApiResponse(code = 401, message = "Unauthorized", response = CloudCompilerrErrorResponse.class),

			@ApiResponse(code = 500, message = "Internal Server Error", response = CloudCompilerrErrorResponse.class)})

	@ResponseStatus(code = HttpStatus.OK)

	@GetMapping(value = "/calljob/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@Traceable
	public String invokeScheduler(
			@ApiParam(name = "id", defaultValue = "1", required = false) @PathVariable("id") int id) {
		Logger.info(
				"Thread in invokeScheduler before calling scheduler method  is {}",
				Thread.currentThread().getName());
		cloudCompilerrScheduler.scheduleJob();
		Logger.info("inside rest controller");
		Logger.info(
				"Thread in invokeScheduler after calling scheduler method is {}",
				Thread.currentThread().getName());
		return "Triggered successfully";
	}

}

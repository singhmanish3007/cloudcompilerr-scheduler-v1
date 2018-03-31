package com.cloudcompilerr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class CloudCompilerrApplication {

    public static void main(String args[]) {
	Logger.info("inside " + new Object() {
	}.getClass().getEnclosingMethod().getName());
	SpringApplication.run(CloudCompilerrApplication.class, args);
    }

}

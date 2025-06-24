package com.alienworkspace.cdr.cdr_configserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class CdrConfigserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(CdrConfigserverApplication.class, args);
	}

}

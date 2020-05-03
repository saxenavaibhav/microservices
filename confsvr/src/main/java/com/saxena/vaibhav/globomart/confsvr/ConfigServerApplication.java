package com.saxena.vaibhav.globomart.confsvr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * 
 * The Global configuration server. This would help separate the configuration and hold it in a centralized location.
 * 
 * @author Vaibhav.Saxena
 *
 */
@SpringBootApplication
@EnableConfigServer
public class ConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConfigServerApplication.class, args);
	}	
}

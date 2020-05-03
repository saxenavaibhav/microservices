package com.saxena.vaibhav.globomart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * 
 * Entry point for ProductCatalogService. This defines a @RefreshScope to enable
 * refreshing the context when property files are updated (using /refresh
 * endpoint).
 * 
 * @author Vaibhav.Saxena
 *
 */
@SpringBootApplication
@RefreshScope
@EnableCircuitBreaker
public class ProductCatalogueServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductCatalogueServiceApplication.class, args);
	}

	/**
	 * 
	 * @return an instance of the RESTTemplate. This is for demo purposes on how we
	 *         can use client side load balancing using RIBBON backed RestTemplate.
	 *         When we make a call to another service, we make use of this
	 *         RESTTemplate instance.
	 */
	@Bean
	@LoadBalanced
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}
}

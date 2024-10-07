package com.apigateway.api_gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ApiGatewayApplication {

	public static void main(String[] args) {
		//url to test:http://localhost:8765/quiz-service/quizz/get/2
		SpringApplication.run(ApiGatewayApplication.class, args);

	}

}

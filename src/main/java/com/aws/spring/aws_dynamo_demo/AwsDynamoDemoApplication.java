package com.aws.spring.aws_dynamo_demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.aws.spring.aws_dynamo_demo.config.CreateDynamoDbTable;

@SpringBootApplication
public class AwsDynamoDemoApplication {

	public static void main(String[] args) {
		//SpringApplication.run(AwsDynamoDemoApplication.class, args);
		SpringApplication app = new SpringApplication(AwsDynamoDemoApplication.class);
		app.addListeners(new CreateDynamoDbTable());
		app.run(args);
	}

}

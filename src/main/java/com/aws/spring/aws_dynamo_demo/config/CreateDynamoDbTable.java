package com.aws.spring.aws_dynamo_demo.config;

import com.aws.spring.aws_dynamo_demo.domain.ProductInfo;

import lombok.extern.slf4j.Slf4j;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;

import software.amazon.awssdk.core.internal.waiters.ResponseOrException;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.DescribeTableRequest;
import software.amazon.awssdk.services.dynamodb.model.DescribeTableResponse;
import software.amazon.awssdk.services.dynamodb.model.TableDescription;
import software.amazon.awssdk.services.dynamodb.waiters.DynamoDbWaiter;

@Slf4j
public class CreateDynamoDbTable implements ApplicationListener<ApplicationReadyEvent> {
    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        DynamoDbTable<ProductInfo> productInfoTable = event.getApplicationContext().getBean(DynamoDbTable.class);
        DynamoDbClient dynamoDbClient = event.getApplicationContext().getBean(DynamoDbClient.class);
        if(!tableExists(dynamoDbClient,ProductInfo.tableName())) {
        	
        	log.info("Attempting to create table {}",ProductInfo.tableName());
        	productInfoTable.createTable();
        	
        	try(DynamoDbWaiter waither = DynamoDbWaiter.builder().client(dynamoDbClient).build()){
        		
        		ResponseOrException<DescribeTableResponse> response = waither
        				.waitUntilTableExists(builder -> builder.tableName(ProductInfo.tableName()).build())
        				.matched();
        		response.response()
        		.orElseThrow(() -> new RuntimeException(ProductInfo.tableName()+" was not created."));
        		
        		log.info("Table {} is created",ProductInfo.tableName());
        	}
        	
        }else {
        	log.info("Table {} already exsits.",ProductInfo.tableName());
        }

    }

	private boolean tableExists(DynamoDbClient dynamoDbClient, String tableName) {
		
		DescribeTableRequest request = DescribeTableRequest.builder()
				.tableName(tableName)
				.build();
		try {
			TableDescription tableInfo = dynamoDbClient.describeTable(request).table();
			if(tableInfo!=null) {
				return true;
			}
		} catch (Exception e) {
			log.error("Error while fetching table details {}",e.getMessage());
		}
		return false;
	}
}

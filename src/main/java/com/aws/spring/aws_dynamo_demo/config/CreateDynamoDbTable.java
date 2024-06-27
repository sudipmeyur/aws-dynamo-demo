package com.aws.spring.aws_dynamo_demo.config;

import com.aws.spring.aws_dynamo_demo.domain.ProductInfo;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

public class CreateDynamoDbTable implements ApplicationListener<ApplicationReadyEvent> {
    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        DynamoDbTable<ProductInfo> productInfoTable = event.getApplicationContext().getBean(DynamoDbTable.class);
        DynamoDbClient dynamoDbClient = event.getApplicationContext().getBean(DynamoDbClient.class);

    }
}

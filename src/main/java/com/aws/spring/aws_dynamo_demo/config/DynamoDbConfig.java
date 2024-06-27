package com.aws.spring.aws_dynamo_demo.config;

import com.aws.spring.aws_dynamo_demo.domain.ProductInfo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

@Configuration
public class DynamoDbConfig {

    @Bean
    public DynamoDbClient dynamoDbClient(){
        return DynamoDbClient.builder().build();
    }

    @Bean
    public DynamoDbEnhancedClient dynamoDbEnhancedClient(){
        return DynamoDbEnhancedClient.builder()
                .dynamoDbClient(dynamoDbClient())
                .build();
    }

    @Bean
    public DynamoDbTable<ProductInfo> productInfoTable(){
       return  dynamoDbEnhancedClient().table(ProductInfo.tableName(), TableSchema.fromBean(ProductInfo.class));
    }
}

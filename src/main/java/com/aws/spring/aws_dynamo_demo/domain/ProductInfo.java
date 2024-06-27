package com.aws.spring.aws_dynamo_demo.domain;

import lombok.Setter;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

@DynamoDbBean
@Setter
public class ProductInfo {
    private String id;
    private String msrp;
    private String cost;

    @DynamoDbPartitionKey
    @DynamoDbAttribute("id")
    public String getId() {
        return id;
    }
    @DynamoDbAttribute("msrp")
    public String getMsrp() {
        return msrp;
    }

    @DynamoDbAttribute("cost")
    public String getCost() {
        return cost;
    }

    public static String tableName(){
        return "ProductInfo";
    }



}

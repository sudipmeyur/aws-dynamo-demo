package com.aws.spring.aws_dynamo_demo.repository;

import org.springframework.stereotype.Repository;

import com.aws.spring.aws_dynamo_demo.domain.ProductInfo;

import lombok.AllArgsConstructor;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Key;

@Repository
@AllArgsConstructor
public class ProductInfoRepository {

	private DynamoDbTable<ProductInfo> productInfoTable;
	
	public ProductInfo create(ProductInfo productInfo) {
		productInfoTable.putItem(productInfo);
		return productInfo;
	}
	public ProductInfo get(ProductInfo productInfo) {
		return productInfoTable.getItem(productInfo);
	}
	public ProductInfo update(ProductInfo productInfo) {
		return productInfoTable.updateItem(productInfo);
	}
	public ProductInfo delete(String productInfoId) {
		Key deletekey = Key.builder().partitionValue(productInfoId).build();
		return productInfoTable.deleteItem(deletekey);
	}

}

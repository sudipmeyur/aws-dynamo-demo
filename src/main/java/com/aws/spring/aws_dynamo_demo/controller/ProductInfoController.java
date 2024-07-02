package com.aws.spring.aws_dynamo_demo.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.aws.spring.aws_dynamo_demo.domain.ProductInfo;
import com.aws.spring.aws_dynamo_demo.repository.ProductInfoRepository;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class ProductInfoController {
	
	public ProductInfoRepository repo;
	
	@GetMapping("/product/{id}")
	public ProductInfo getProduct(@PathVariable String id) {
		ProductInfo product = new ProductInfo();
		product.setId(id);
		return repo.get(product);
	}
	
	@PostMapping("/product")
	public ProductInfo createProduct(@RequestBody ProductInfo productInfo) {
		return repo.create(productInfo);
	}
	
	@PutMapping("/product/{id}")
	public void updateProduct(@PathVariable String id,@RequestBody ProductInfo productInfo) {
		if(productInfo.getId().equals(id)) {
			repo.update(productInfo);
		}
	}
	
	@DeleteMapping("/product/{id}")
	public void deleteProduct(@PathVariable String id) {
		repo.delete(id);
	}

	

}

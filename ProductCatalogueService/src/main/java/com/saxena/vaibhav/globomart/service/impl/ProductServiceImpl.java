package com.saxena.vaibhav.globomart.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.saxena.vaibhav.globomart.config.ServiceConfig;
import com.saxena.vaibhav.globomart.dto.ProductDto;
import com.saxena.vaibhav.globomart.mapper.ProductMapper;
import com.saxena.vaibhav.globomart.model.Product;
import com.saxena.vaibhav.globomart.repository.ProductRepository;
import com.saxena.vaibhav.globomart.service.ProductService;

import lombok.extern.java.Log;

/**
 * 
 * Productservice  concrete implementation.
 * 
 * @author Vaibhav.Saxena
 *
 */
@Service
@Log
public class ProductServiceImpl implements ProductService {

	private ProductRepository productRepository;

	@Autowired
	ServiceConfig config;

	private ProductMapper productMapper;

	public ProductServiceImpl(final ProductRepository productRepository, final ProductMapper productMapper) {
		super();
		this.productRepository = productRepository;
		this.productMapper = productMapper;
	}


	/**
	 * Find products by producttype. Defines Hystrix configuration for short circuit and follows bulkhead pattern for fallback.
	 */
	@Override
	@HystrixCommand(fallbackMethod = "buildFallbackProductList",
			commandProperties = {@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "12000") })
	public List<ProductDto> findByProductType(final String productType) {
		log.info("Inside ProductService: Getting prodducts with the ProductType" + productType);
		config.getExampleProperty();

		return productRepository.findByProductType(productType).stream().map(product -> {
			ProductDto productDto = productMapper.productToProductDTO(product);
			return productDto;
		}).collect(Collectors.toList());

	}

	@Override
	@HystrixCommand
	public ProductDto addProduct(final ProductDto productDto) {
		return saveAndReturnDTO(productMapper.productDtoToProduct(productDto));
	}

	@Override
	@HystrixCommand
	public void deleteProduct(final String productId) {
		productRepository.deleteById(productId);
	}

	@SuppressWarnings("unused")
	private List<ProductDto> buildFallbackProductList(String productType) {
		List<ProductDto> fallbackList = new ArrayList<>();
		ProductDto license = new ProductDto()
				.withName("No Product")
				.withDescription("No Products available")
				.withType("Type");

		fallbackList.add(license);
		return fallbackList;
	}

	private ProductDto saveAndReturnDTO(Product product) {
		Product savedProduct = productRepository.save(product);
		ProductDto returnDto = productMapper.productToProductDTO(savedProduct);
		return returnDto;
	}
}

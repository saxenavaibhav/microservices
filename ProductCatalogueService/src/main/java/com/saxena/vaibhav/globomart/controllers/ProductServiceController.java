package com.saxena.vaibhav.globomart.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.saxena.vaibhav.globomart.dto.ProductDto;
import com.saxena.vaibhav.globomart.service.ProductService;

import lombok.extern.java.Log;

/**
 * The main controller for the Product Microservice. 
 * 
 * @author Vaibhav.Saxena
 *
 */
@RestController
@RequestMapping(value = ProductServiceController.BASE_URL)
@Log
public class ProductServiceController {

	static final String BASE_URL = "v1/catalog/{catalogId}/product";
	
	private ProductService productService;
	
	public ProductServiceController(final ProductService productService) {
		super();
		this.productService = productService;
	}

	/**
	 * 
	 * Get the list of products that have productType as {productType}. 
	 * 
	 * @param productType the productType (Search criteria).
	 * @return List of products.
	 * @throws Exception
	 */
	@GetMapping(value = "producttype/{producttype}")
	@ResponseStatus(HttpStatus.OK)
	public List<ProductDto> getProductsByType(@PathVariable("producttype") String productType) throws Exception {
		log.info("Getting prodducts with the ProductType" + productType);
		return productService.findByProductType(productType);
	}
	
	/**
	 * 
	 * Create a new product in the system.
	 * 
	 * @param productDto the product object to be persisted.
	 * @return The persisted product object.
	 * @throws Exception
	 */
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ProductDto addProduct(@RequestBody ProductDto productDto) throws Exception {
		log.info("Adding a new product: " + productDto);
	    return productService.addProduct(productDto);
	}
	
	/**
	 * 
	 * Delete the record with the given product ID.
	 * 
	 * @param productId the product ID.
	 * @throws Exception
	 */
	@DeleteMapping("value = {productId}")
	@ResponseStatus(HttpStatus.OK)
	public void deleteProduct(@PathVariable("productId") String productId) throws Exception {
		
		log.info("Deleting the product with ID: " + productId);
		productService.deleteProduct(productId);
	}
}

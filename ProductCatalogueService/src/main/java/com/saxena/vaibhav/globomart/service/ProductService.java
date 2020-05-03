package com.saxena.vaibhav.globomart.service;

import java.util.List;

import com.saxena.vaibhav.globomart.dto.ProductDto;

/**
 * 
 * The productservice interface. Exposes three methods to perform different operations on products.
 * 
 * @author Vaibhav.Saxena
 *
 */
public interface ProductService {

	List<ProductDto> findByProductType(final String productType);
	
	ProductDto addProduct(final ProductDto productDto);
	
	void deleteProduct(final String productId);
	
}

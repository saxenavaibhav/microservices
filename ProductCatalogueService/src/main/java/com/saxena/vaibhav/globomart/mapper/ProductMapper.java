package com.saxena.vaibhav.globomart.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.saxena.vaibhav.globomart.dto.ProductDto;
import com.saxena.vaibhav.globomart.model.Product;

/**
 * 
 * Product Mapstruct mapper that converts Product instance to a ProductDto instance and vice versa.
 * 
 * @author Vaibhav.Saxena
 *
 */
@Mapper
public interface ProductMapper {

	ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

	ProductDto productToProductDTO(Product product);

	Product productDtoToProduct(ProductDto productDto);
}

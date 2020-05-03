package com.saxena.vaibhav.globomart.dto;

import lombok.ToString;

/**
 * The product representation that is shared iwth the client.
 * 
 * 
 * @author Vaibhav.Saxena
 *
 */
@ToString
public class ProductDto {

	private String id;
	
	private String name;
	
	private String description;
	
	private String productType;

	public String getId() {
		return id;
	}

	public void setId(final String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(final String productType) {
		this.productType = productType;
	}

	public ProductDto withId(final String id) {
		this.setId(id);
		return this;
	}
	
	public ProductDto withName(final String name) {
		this.setName(name);
		return this;
	}
	
	public ProductDto withDescription(final String description) {
		this.setDescription(description);
		return this;
	}
	
	public ProductDto withType(final String type) {
		this.setProductType(type);
		return this;
	}
	
}

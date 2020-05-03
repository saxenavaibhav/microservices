package com.saxena.vaibhav.globomart.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.ToString;

@ToString
@Entity
@Table(name = "PRODUCTS")
public class Product {

	@Id
	@Column(name = "ID", nullable = false)
	private String id;
	
	@Column(name = "PRODUCT_NAME", nullable = false)
	private String name;
	
	@Column(name = "PRODUCT_DESCRIPTION")
	private String description;
	
	@Column(name = "PRODUCT_TYPE", nullable = false)
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

	public Product withId(final String id) {
		this.setId(id);
		return this;
	}
	
	public Product withName(final String name) {
		this.setName(name);
		return this;
	}
	
	public Product withDescription(final String description) {
		this.setDescription(description);
		return this;
	}
	
	public Product withType(final String type) {
		this.setProductType(type);
		return this;
	}
	
}

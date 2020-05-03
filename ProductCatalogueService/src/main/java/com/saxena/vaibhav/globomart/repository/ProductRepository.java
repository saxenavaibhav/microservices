package com.saxena.vaibhav.globomart.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.saxena.vaibhav.globomart.model.Product;

/**
 * The generic CRUD repository for Products.
 * 
 * @author Vaibhav.Saxena
 *
 */
@Repository
public interface ProductRepository extends CrudRepository<Product, String> {

	public List<Product> findByProductType(final String productType);
}

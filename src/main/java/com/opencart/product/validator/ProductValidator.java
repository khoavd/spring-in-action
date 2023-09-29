package com.opencart.product.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import com.opencart.product.dto.Product;
import com.opencart.product.entity.ProductEntity;
import com.opencart.product.repository.ProductRepository;

@Component
public class ProductValidator {
	
	private final ProductRepository repo;

	public ProductValidator(ProductRepository repo) {
		super();
		this.repo = repo;
	}
	
	public void validateForAdd(Errors errors, Product product) {
		
		ProductEntity entity = repo.readByNameIgnoreCase(product.getName());
		
		if (entity == null) {
			return;
		}
		
		errors.rejectValue("name", "DUP", "Name is already used by an existing object");
		
	}
	
	public void validateForUpdate(Errors errors, Product product) {
		
		ProductEntity entity = repo.readByNameIgnoreCase(product.getName());
		
		if (entity == null) {
			return;
		}
		
		if (entity.getId() == product.getId()) {
			return;
		}
		
		errors.rejectValue("name", "DUP", "Name is already used by an existing object");
		
	}
}

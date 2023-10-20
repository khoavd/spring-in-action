package com.opencart.product.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import com.opencart.product.dto.ProductCategory;
import com.opencart.product.entity.ProductCategoryEntity;
import com.opencart.product.repository.ProductCategoryRepository;

@Component
public class CategoryValidator {
	
	private final ProductCategoryRepository repo;
	
	public CategoryValidator(ProductCategoryRepository repo) {
		super();
		this.repo = repo;
	}

	public void validateName(Errors errors, ProductCategory model, Long savedId) {
		
		ProductCategoryEntity category = repo.findByNameIgnoreCase(model.getName());
		
		if (category == null) {
			return;
		}
				
		if (savedId == null || savedId.longValue() != category.getId()) {
			errors.rejectValue("name", "DUP", "Name is already used by an existing object");
		}
		
	}
	
	public void validateNameForAddNew(Errors errors, ProductCategory model) {
		
		ProductCategoryEntity category = repo.findByNameIgnoreCase(model.getName());
		
		if (category == null) {
			return;
		}
		
		errors.rejectValue("name", "DUP", "Name is already used by an existing object");
	}
}

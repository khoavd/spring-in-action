package com.opencart.product.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.opencart.product.dto.ProductCategory;
import com.opencart.product.mapper.ProductCategoryMapper;
import com.opencart.product.repository.ProductCategoryRepository;

@Service
public class ProductCategoryService {
	
	private final ProductCategoryRepository repo;
	
	private final ProductCategoryMapper mapper;
	
	public ProductCategoryService(ProductCategoryRepository repo, ProductCategoryMapper mapper) {
		super();
		this.repo = repo;
		this.mapper = mapper;
	}

	public ProductCategory addProductCategory(ProductCategory model) {
		return mapper.mapProductCategoryFromProductCategoryEntity(
				repo.save(
						mapper.mapProductCategoryEntityFromProductCategory(model)));
	}
	
	public List<ProductCategory> getAllProductCategory() {
		return mapper.mapProductCategoriesFromProductCategoryEntities(repo.findAll());
	}
}

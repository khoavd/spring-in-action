package com.opencart.product.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.opencart.product.dto.ProductCategories;
import com.opencart.product.dto.ProductCategory;
import com.opencart.product.entity.ProductCategoryEntity;

@Component
public class ProductCategoryMapper {
	
	public ProductCategoryEntity mapProductCategoryEntityFromProductCategory(ProductCategory from) {
		ProductCategoryEntity to = new ProductCategoryEntity();
		
		to.setId(from.getId());
		to.setName(from.getName());
		
		return to;
	}
	
	public ProductCategory mapProductCategoryFromProductCategoryEntity(ProductCategoryEntity from) {
		ProductCategory to = new ProductCategory();
		
		to.setId(from.getId());
		to.setName(from.getName());
		
		return to;
	}
	
	public ProductCategories mapProductCategoriesFromProductCategoryEntities(List<ProductCategoryEntity> from) {
		return from.stream()
				.map(this::mapProductCategoryFromProductCategoryEntity)
				.collect(Collectors.toCollection(ProductCategories::new));
	}
	
}

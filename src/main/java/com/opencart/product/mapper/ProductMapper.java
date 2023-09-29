package com.opencart.product.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.opencart.product.dto.Product;
import com.opencart.product.dto.Products;
import com.opencart.product.entity.ProductEntity;
import com.opencart.product.repository.ProductCategoryRepository;

@Component
public class ProductMapper {
	
	private final ProductCategoryRepository categoryRepo;
	
	private final ProductCategoryMapper cateMapper;
	
	public ProductMapper(
			ProductCategoryRepository categoryRepo,
			ProductCategoryMapper cateMapper) {
		super();
		this.categoryRepo = categoryRepo;
		this.cateMapper = cateMapper;
	}

	public ProductEntity mapProductEntityFromProduct(Product from) {
		ProductEntity to = new ProductEntity();
		
		to.setId(from.getId());
		to.setName(from.getName());
		to.setCategory(categoryRepo.findById(from.getCateId()).get());
		to.setSortDescription(from.getSortDescription());
		to.setDescription(from.getDescription());
		to.setPrice(from.getPrice());
		
		return to;
	}
	
	public Product mapProductFromProductEntity(ProductEntity from) {
		
		Product to = new Product();
		
		to.setId(from.getId());
		to.setCategory(
				cateMapper.mapProductCategoryFromProductCategoryEntity(
						from.getCategory()));
		to.setSortDescription(from.getSortDescription());
		to.setDescription(from.getDescription());
		to.setPrice(from.getPrice());
		
		return to;
	}
	
	public Products mapProductsFromProductEntities(List<ProductEntity> from) {
		return from.stream()
				.map(this::mapProductFromProductEntity)
				.collect(Collectors.toCollection(Products::new));
	}
}

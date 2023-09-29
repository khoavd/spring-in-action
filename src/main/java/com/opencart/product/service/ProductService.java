package com.opencart.product.service;

import org.springframework.stereotype.Service;

import com.opencart.product.dto.Product;
import com.opencart.product.mapper.ProductMapper;
import com.opencart.product.repository.ProductRepository;

@Service
public class ProductService {
	
	private final ProductRepository repo;
	
	private final ProductMapper mapper;
	
	public ProductService(ProductRepository repo, ProductMapper mapper) {
		super();
		this.repo = repo;
		this.mapper = mapper;
	}

	public Product saveProduct(Product product) {
		return mapper.
				mapProductFromProductEntity(
						repo.save(
								mapper.mapProductEntityFromProduct(product)));
	}
}

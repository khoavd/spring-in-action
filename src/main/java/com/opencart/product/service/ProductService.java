package com.opencart.product.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.opencart.product.dto.Product;
import com.opencart.product.dto.Products;
import com.opencart.product.entity.ProductEntity;
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
	
	public Products getAllProducts() {
		return mapper.mapProductsFromProductEntities(repo.findAll());
	}
	
	public Product getProductById(long id) {
		Optional<ProductEntity> entity = repo.findById(id);
				
		if (entity.isEmpty()) {
			return null;
		}
		
		return mapper.mapProductFromProductEntity(entity.get());
	}
}

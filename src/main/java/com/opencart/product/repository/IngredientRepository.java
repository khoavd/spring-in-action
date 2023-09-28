package com.opencart.product.repository;

import java.util.Optional;

import com.opencart.product.dto.Ingredient;

public interface IngredientRepository {
	
	Iterable<Ingredient> findAll();
	
	Optional<Ingredient> findById(String id);
	
	Ingredient save(Ingredient ingredient); 
}	

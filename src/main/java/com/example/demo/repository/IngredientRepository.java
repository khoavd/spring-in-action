package com.example.demo.repository;

import java.util.Optional;

import com.example.demo.domain.Ingredient;

public interface IngredientRepository {
	
	Iterable<Ingredient> findAll();
	
	Optional<Ingredient> findById(String id);
	
	Ingredient save(Ingredient ingredient); 
}	

package com.example.demo.repository;

import java.util.List;

import com.example.demo.domain.Ingredient;

public interface IngredientRefRepository {
	void saveIngredientRefs(long tacoId, List<Ingredient> ingredients);
}

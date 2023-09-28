package com.opencart.product.repository;

import java.util.List;

import com.opencart.product.dto.Ingredient;

public interface IngredientRefRepository {
	void saveIngredientRefs(long tacoId, List<Ingredient> ingredients);
}

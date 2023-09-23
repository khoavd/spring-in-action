package com.example.demo.repository;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Ingredient;

@Repository
public class JdbcIngredientRefRepository implements IngredientRefRepository {
	
	private final JdbcTemplate jdbcTemplate;

	public JdbcIngredientRefRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	private final String SQL_INSERT = "insert into Ingredient_Ref (ingredient, taco, taco_key) values (?, ?, ?)";
	
	@Override
	public void saveIngredientRefs(long tacoId, List<Ingredient> ingredients) {
		int key = 0;
		
		for (Ingredient ingredient : ingredients) {
			jdbcTemplate.update(SQL_INSERT, 
					ingredient.getId(), tacoId, key++);
		}
		
	}

}

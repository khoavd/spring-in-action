package com.example.demo.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Ingredient;
import com.example.demo.utils.IngredientKeys;

@Repository
public class JdbcIngredientRepository implements IngredientRepository {
	
	private final JdbcTemplate jdbcTemplate;
	
	public JdbcIngredientRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	
	private static final String SQL_FIND_ALL = "select id, name, type from Ingredient";
	private static final String SQL_FIND_BY_ID = SQL_FIND_ALL + " where id = ?";
	private static final String SQL_INSERT = "insert into Ingredient (id, name, type) values (?, ?, ?)";

	@Override
	public Iterable<Ingredient> findAll() {
		return jdbcTemplate.query(SQL_FIND_ALL, this::mapRowToIngredient);
	}

	@Override
	public Optional<Ingredient> findById(String id) {
		
		List<Ingredient> results = 
				jdbcTemplate.query(SQL_FIND_BY_ID, this::mapRowToIngredient, id);
		
		if (results.size() == 0) {
			return Optional.empty();
		} else {
			return Optional.of(results.get(0));
		}
	}

	@Override
	public Ingredient save(Ingredient ingredient) {
		
		jdbcTemplate.update(SQL_INSERT, ingredient.getId(), ingredient.getName(), ingredient.getType().toString());
		
		return ingredient;
	}
	
	private Ingredient mapRowToIngredient(ResultSet row, int rowNum) 
			throws SQLException {
		
		return new Ingredient(
				row.getString(IngredientKeys.ID), 
				row.getString(IngredientKeys.NAME), 
				Ingredient.Type.valueOf(row.getString(IngredientKeys.TYPE)));
	}
	
}

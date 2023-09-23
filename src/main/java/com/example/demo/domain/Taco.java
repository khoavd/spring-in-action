package com.example.demo.domain;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Taco {
	
	private long id;
	
	@NotNull
	@Size(min = 5, message = "Name must be at least 5 characters long")
	private String name;
	
	@NotNull
	@Size(min = 1, message = "You must choose at least 1 ingredient")
	private List<Ingredient> ingredients;
	
	private Date createdAt;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Ingredient> getIngredients() {
		return ingredients;
	}

	public void setIngredients(List<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
}

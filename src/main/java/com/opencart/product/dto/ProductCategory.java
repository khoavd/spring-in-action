package com.opencart.product.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ProductCategory {
	
	private long id;
	
	@NotNull(message = "Name can not be null")
	@Size(min = 5, message = "Name must be at least 5 characters long")
	private String name;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}

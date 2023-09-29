package com.opencart.product.dto;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Product {
	
	private long id;
	
	@NotNull
	@NotBlank
	@Size(min = 5, message = "Name must be at least 5 characters long")
	private String name;
	
	@Min(1)
	private long cateId;
	
	@NotNull
	@NotBlank
	@Size(max = 2500, message = "Short description is greater than 2500 characters")
	private String sortDescription;
	
	@NotNull
	@NotBlank
	@Size(max = 10000, message = "Description is greater than 10000 characters")
	private String description;
	
	@DecimalMin("0.0")
	@DecimalMax("10000.0")
	private double price;
	
	private ProductCategory category;

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

	public long getCateId() {
		return cateId;
	}

	public void setCateId(long cateId) {
		this.cateId = cateId;
	}

	public String getSortDescription() {
		return sortDescription;
	}

	public void setSortDescription(String sortDescription) {
		this.sortDescription = sortDescription;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public ProductCategory getCategory() {
		return category;
	}

	public void setCategory(ProductCategory category) {
		this.category = category;
	}
}

package com.opencart.product.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/products")
public class ProductAdminController {
	
	@GetMapping("/add")
	public String addProductForm() {
		return "products/add";
	}
	
	@GetMapping("/add-category")
	public String addCategoryForm() {
		return "products/add-category";
	}
}

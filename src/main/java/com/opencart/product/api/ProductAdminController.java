package com.opencart.product.api;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.opencart.product.dto.ProductCategory;
import com.opencart.product.service.ProductCategoryService;
import com.opencart.product.service.ProductService;
import com.opencart.product.validator.ProductValidator;

@Controller
@RequestMapping("/products")
public class ProductAdminController {
	
	private final ProductService service;
	
	private final ProductValidator validator;
	
	private final ProductCategoryService cateService;
	
	public ProductAdminController(ProductService service, ProductValidator validator,
			ProductCategoryService cateService) {
		super();
		this.service = service;
		this.validator = validator;
		this.cateService = cateService;
	}
	
	@ModelAttribute
	public void addCategoriesToModel(Model model) {
		model.addAttribute("categories", cateService.getAllProductCategory());
	}

	@GetMapping("/admin/add")
	public String addProductForm(Model model) {
		
		model.addAttribute("categories", cateService.getAllProductCategory());
		
		return "products/add";
	}
	

}

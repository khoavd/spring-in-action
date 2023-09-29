package com.opencart.product.api;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.opencart.product.dto.Product;
import com.opencart.product.service.ProductCategoryService;
import com.opencart.product.service.ProductService;
import com.opencart.product.validator.ProductValidator;

@Controller
@RequestMapping("/products")
@SessionAttributes("product")
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
	public String addProductForm() {
		
		return "redirect:/products/add";
	}
	
	@PostMapping("/admin/add")
	public String addProduct(
			Model model, 
			@Valid Product product,
			Errors errors,
			SessionStatus sessionStatus) {
		
		validator.validateForAdd(errors, product);
		
		if (errors.hasErrors()) {
			return "/products/add";
		}
		
		service.saveProduct(product);
		
		sessionStatus.setComplete();
		
		return "redirect:/products/add";
	}
	
	@ModelAttribute(name = "product")
	public Product product() {
		return new Product();
	}
}

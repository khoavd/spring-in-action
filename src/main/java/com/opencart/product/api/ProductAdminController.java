package com.opencart.product.api;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
		model.addAttribute("products", service.getAllProducts());
	}

	@GetMapping("/admin/update/{id}")
	public String updateProductForm(@PathVariable(value = "id") long id, Model model) {
		
		Product product = service.getProductById(id);
		
		if (product == null) {
			return "404";
		}
		
		model.addAttribute("product", product);
		
		return "/products/add";
	}
	
	@GetMapping("/admin/add")
	public String addProductForm() {
		
		return "/products/add";
	}
	
	@GetMapping("/admin/list")
	public String listProductForm() {
		
		return "/products/products-list";
	}
	
	@PostMapping("/admin/update")
	public String updateProduct(
			Model model, 
			@Valid Product product,
			Errors errors,
			SessionStatus sessionStatus) {
		
		validator.validateForUpdate(errors, product);
		
		if (errors.hasErrors()) {
			return "/products/add";
		}
		
		service.saveProduct(product);
		
		sessionStatus.setComplete();
		
		return "redirect:/products/admin/list";
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
		
		return "redirect:/products/admin/list";
	}
	
	
	@ModelAttribute(name = "product")
	public Product product() {
		return new Product();
	}
}

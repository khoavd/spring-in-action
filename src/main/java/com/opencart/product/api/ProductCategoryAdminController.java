package com.opencart.product.api;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.opencart.product.dto.ProductCategory;
import com.opencart.product.service.ProductCategoryService;
import com.opencart.product.validator.CategoryValidator;

@Controller
@RequestMapping("/products")
@SessionAttributes("productCategory")
public class ProductCategoryAdminController {
	
	private final ProductCategoryService service;
	
	private final CategoryValidator validator;
		
	Logger logger = LoggerFactory.getLogger(ProductCategoryAdminController.class);

	public ProductCategoryAdminController(
			ProductCategoryService service, 
			CategoryValidator validator) {
		super();
		this.service = service;
		this.validator = validator;
	}

	@ModelAttribute
	public void addCategoriesToModel(Model model) {
		model.addAttribute("categories", service.getAllProductCategory());
	}
	
	@GetMapping("/admin/add-category*")
	public String viewAddCategoryForm() {
		
		return "products/add-category";
	}
	
	@PostMapping("/admin/add-category")
	public String saveCategory(
			@Valid ProductCategory productCategory,
			Errors errors, 
			HttpServletRequest request) {
		
		Long savedId = (Long) request.getSession().getAttribute("savedId");
		
		validator.validateName(errors, productCategory, savedId);
		
		if (errors.hasErrors()) {
			return "/products/add-category";
		}
		
		logger.info(productCategory.getName());
		
		if (savedId != null) {
			productCategory.setId(savedId.longValue());
		}
		
		ProductCategory saved = 
				service.addProductCategory(productCategory);
		
		request.getSession().setAttribute("savedId", saved.getId());
		
		return "redirect:/products/admin/add-category";
	}
	
	@PostMapping("/admin/add-category/new")
	public String saveAndAddCategory(
			@Valid ProductCategory productCategory,
			Errors errors, 
			SessionStatus sessionStatus,
			HttpServletRequest request) {
				
		validator.validateNameForAddNew(errors, productCategory);

		if (errors.hasErrors()) {
			return "products/add-category";
		}
		
		logger.info(productCategory.getName());
		
		productCategory = 
				service.addProductCategory(productCategory);
		
		request.getSession().setAttribute("savedId", null);

		sessionStatus.setComplete();
		
		return "redirect:/products/admin/add-category";
	}
	
	
	@PostMapping("/admin/add-category/cancel")
	public String cancelAddCategory(
			SessionStatus sessionStatus,
			HttpServletRequest request) {
		
		sessionStatus.setComplete();
		request.getSession().setAttribute("savedId", null);

		return "redirect:/products/admin/add-category";
	}
	
	@ModelAttribute(name = "productCategory")
	public ProductCategory productCategory() {
		return new ProductCategory();
	}
}

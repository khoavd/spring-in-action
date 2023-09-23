package com.example.demo.api;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

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

import com.example.demo.domain.Ingredient;
import com.example.demo.domain.Ingredient.Type;
import com.example.demo.domain.Taco;
import com.example.demo.domain.TacoOrder;
import com.example.demo.repository.IngredientRepository;

@Controller
@RequestMapping("/design")
@SessionAttributes("tacoOrder")
public class DesignTacoController {
	
	Logger logger = LoggerFactory.getLogger(DesignTacoController.class);
	
	private final IngredientRepository ingredientRepository;
	
	public DesignTacoController(IngredientRepository ingredientRepository) {
		this.ingredientRepository = ingredientRepository;
	}
	

	@ModelAttribute
	public void addIngredientsToModel(Model model) {
		
		Iterable<Ingredient> ingredients = ingredientRepository.findAll();
		
		Type[] types = Ingredient.Type.values();
		
		for (Type type : types) {
			model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type));
		}
	}
	
	@PostMapping
	public String processTaco(@Valid Taco taco, 
			Errors errors, 
			@ModelAttribute TacoOrder tacoOrder) {
		
		if (errors.hasErrors()) {
			return "design";
		}
		
		tacoOrder.addTaco(taco);
		
		logger.info("Processing taco: {}", taco);
		
		return "redirect:/orders/current";
	}
	
	@ModelAttribute(name = "tacoOrder")
	public TacoOrder order() {
		return new TacoOrder();
	}
	
	@ModelAttribute(name = "taco")
	public Taco taco() {
		return new Taco();
	}
	
	@GetMapping
	public String showDesignForm() {
		return "design";
	}
	
	@ModelAttribute(name = "message")
	public String setMsg() {
		return "Dogoo Office";
	}

	private Iterable<Ingredient> filterByType(Iterable<Ingredient> ingredients, Type type) {
		
		return StreamSupport.stream(ingredients.spliterator(), false)
				.filter(x -> x.getType().equals(type)).collect(Collectors.toList());
	}
}

package com.example.demo.api;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.example.demo.domain.TacoOrder;
import com.example.demo.repository.JdbcOrderRepository;

@Controller
@RequestMapping("/orders")
@SessionAttributes("tacoOrder")
public class OrderController {
	
	private final JdbcOrderRepository jdbcOrderRepo;
	
	public OrderController(JdbcOrderRepository jdbcOrderRepo) {
		super();
		this.jdbcOrderRepo = jdbcOrderRepo;
	}

	@GetMapping("/current")
	public String orderForm() {
		return "orderForm";
	}
	
	@PostMapping
	public String processOrder(
			@Valid TacoOrder tacoOrder,
			Errors errors,
			SessionStatus sessionStatus) {
		
		if (errors.hasErrors()) {
			return "orderForm";
		}
		
		jdbcOrderRepo.saveOrder(tacoOrder);
		
		sessionStatus.setComplete();
		
		return "redirect:/";
	}

}

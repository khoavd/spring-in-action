package com.example.demo.repository;

import com.example.demo.domain.TacoOrder;

public interface OrderRepository {
	TacoOrder saveOrder(TacoOrder order);
}

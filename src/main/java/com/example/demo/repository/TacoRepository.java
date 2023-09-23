package com.example.demo.repository;

import com.example.demo.domain.Taco;

public interface TacoRepository {
	long saveTaco(long orderId, int oderkey, Taco taco);
}

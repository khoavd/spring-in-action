package com.opencart.product.repository;

import com.opencart.product.dto.Taco;

public interface TacoRepository {
	long saveTaco(long orderId, int oderkey, Taco taco);
}

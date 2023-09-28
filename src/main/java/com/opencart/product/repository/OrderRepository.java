package com.opencart.product.repository;

import com.opencart.product.dto.TacoOrder;

public interface OrderRepository {
	TacoOrder saveOrder(TacoOrder order);
}

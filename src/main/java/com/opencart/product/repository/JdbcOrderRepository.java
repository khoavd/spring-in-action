package com.opencart.product.repository;

import java.sql.Types;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.opencart.product.dto.Taco;
import com.opencart.product.dto.TacoOrder;

@Repository
public class JdbcOrderRepository implements OrderRepository {
	
	private final JdbcTacoRepository jdbcTacoRepo;
	
	private final JdbcOperations jdbcOperations;
	
	private final String SQL_INSERT = "insert into Taco_Order "
			+ "(delivery_name, delivery_street, delivery_city, "
			+ "delivery_state, delivery_zip, cc_number, "
			+ "cc_expiration, cc_cvv, placed_at) "
			+ "values (?,?,?,?,?,?,?,?,?)";
	
	public JdbcOrderRepository(JdbcTacoRepository jdbcTacoRepo, JdbcOperations jdbcOperations) {
		super();
		this.jdbcTacoRepo = jdbcTacoRepo;
		this.jdbcOperations = jdbcOperations;
	}

	@Override
	@Transactional
	public TacoOrder saveOrder(TacoOrder order) {
		PreparedStatementCreatorFactory pscf = 
				new PreparedStatementCreatorFactory(
						SQL_INSERT,
						Types.VARCHAR, Types.VARCHAR, 
						Types.VARCHAR, Types.VARCHAR, 
						Types.VARCHAR, Types.VARCHAR, 
						Types.VARCHAR, Types.VARCHAR, 
						Types.TIMESTAMP);
		
		pscf.setReturnGeneratedKeys(true);
		
		order.setPlacedAt(new Date());
		
		PreparedStatementCreator psc = 
				pscf.newPreparedStatementCreator(
						Arrays.asList(
								order.getDeliveryName(), order.getDeliveryStreet(),
								order.getDeliveryCity(), order.getDeliveryState(),
								order.getDeliveryZip(), order.getCcNumber(),
								order.getCcExpiration(), order.getCcCVV(),
								order.getPlacedAt()));
		
		GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
		
		jdbcOperations.update(psc, keyHolder);
		
		long orderId = keyHolder.getKey().longValue();
		
		order.setId(orderId);
		
		List<Taco> tacos = order.getTacos();
		
		int i = 0;
		
		for (Taco taco : tacos) {
			jdbcTacoRepo.saveTaco(orderId, i++, taco);
		}
		
		return order;
	}
	
}

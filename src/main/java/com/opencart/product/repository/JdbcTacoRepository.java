package com.opencart.product.repository;

import java.sql.Types;
import java.util.Arrays;
import java.util.Date;

import org.springframework.asm.Type;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import com.opencart.product.dto.Taco;

@Repository
public class JdbcTacoRepository implements TacoRepository {
	
	private final JdbcOperations jdbcOperations;
	
	private final JdbcIngredientRefRepository ingredientRefRepo;
	
	private final String SQL_INSERT = "insert into Taco (name, created_at, taco_order, taco_order_key)" +
									" values (?, ?, ?, ?)";
	
	public JdbcTacoRepository(
			JdbcOperations jdbcOperations, 
			JdbcIngredientRefRepository ingredientRefRepo) {
		
		super();
		this.jdbcOperations = jdbcOperations;
		this.ingredientRefRepo = ingredientRefRepo;
	}

	@Override
	public long saveTaco(long orderId, int orderkey, Taco taco) {
		
		taco.setCreatedAt(new Date());
		
		PreparedStatementCreatorFactory pscf = 
				new PreparedStatementCreatorFactory(
						SQL_INSERT, 
						Types.VARCHAR, Types.TIMESTAMP, Type.LONG, Type.LONG);
		
		pscf.setReturnGeneratedKeys(true);
		
		PreparedStatementCreator psc = 
				pscf.newPreparedStatementCreator(
						Arrays.asList(
								taco.getName(), 
								taco.getCreatedAt(), 
								orderId, 
								orderkey));
		
		GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
		
		jdbcOperations.update(psc, keyHolder);
		
		long tacoId = keyHolder.getKey().longValue();
		
		taco.setId(tacoId);
		
		ingredientRefRepo.saveIngredientRefs(tacoId, taco.getIngredients());
		
		return 0;
	}
	
}

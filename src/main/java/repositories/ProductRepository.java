package repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ProductRepository {

	private static final String tableName = "PRODUCT";

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public void addProduct(String name, double price){
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO ").append(tableName).append(" values (null,?,?)");
		jdbcTemplate.update(sql.toString(),name,price);
	}

}

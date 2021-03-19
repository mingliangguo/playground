package ming.data.transmission.repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import ming.data.transmission.model.Product;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * @author gary
 */
@Repository
public class ProductRepository {
    private JdbcTemplate jdbcTemplate;
    private DataSource ds;
    private static final String INSERT_INTO_PRODUCT_DB_SQL = "INSERT INTO product_db (id, name, price, category) VALUES(?, ?, ?, ?);";

    public ProductRepository(DataSource ds) {
        this.ds = ds;
        jdbcTemplate = new JdbcTemplate(ds);
    }
    public void batchCreate(List<Product> list) {
        jdbcTemplate.batchUpdate(INSERT_INTO_PRODUCT_DB_SQL, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setInt(1, list.get(i).getId());
                ps.setString(2, list.get(i).getName());
                ps.setDouble(2, list.get(i).getPrice());
                ps.setInt(2, list.get(i).getCategory());
            }

            @Override
            public int getBatchSize() {
                return list.size();
            }
        });
    }

    public void createProduct(Product p) {
        Object[] params = new Object[] {
                p.getId(),
                p.getName(),
                p.getPrice(),
                p.getCategory()
        };
        jdbcTemplate.update(INSERT_INTO_PRODUCT_DB_SQL, params);
    }

    public List<Product> queryProducts() {
        return jdbcTemplate.query("SELECT * FROM product_db",
                (rs, num) -> Product.builder()
                    .id(rs.getInt("ID"))
                    .name(rs.getString("NAME"))
                    .price(rs.getDouble("PRICE"))
                    .category(rs.getInt("CATEGORY"))
                .build());
    }
}

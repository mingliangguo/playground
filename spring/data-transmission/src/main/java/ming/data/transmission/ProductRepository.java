package ming.data.transmission;

import java.util.List;

import javax.sql.DataSource;

import ming.data.transmission.model.Product;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ProductRepository {
    private JdbcTemplate jdbcTemplate;
    private DataSource ds;

    public ProductRepository(DataSource ds) {
        this.ds = ds;
        jdbcTemplate = new JdbcTemplate(ds);
    }

    public List<Product> queryProducts() {
        return jdbcTemplate.query("select * from product_db",
                (rs, num) -> Product.builder()
                    .id(rs.getInt(1))
                    .name(rs.getString(2))
                    .price(rs.getDouble(3))
                    .category(rs.getInt(4))
                .build());
    }
}

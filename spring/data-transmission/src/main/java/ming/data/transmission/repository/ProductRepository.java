package ming.data.transmission.repository;

import java.sql.Statement;
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

    public void createProduct(Product p) {
        final String sql = "INSERT INTO product_db (id, name, price, category) VALUES(?, ?, ?, ?);";
        Object[] params = new Object[] {
                p.getId(),
                p.getName(),
                p.getPrice(),
                p.getCategory()
        };
        jdbcTemplate.update(sql, params);
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

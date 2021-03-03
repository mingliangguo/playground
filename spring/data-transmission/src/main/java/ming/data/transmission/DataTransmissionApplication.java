package ming.data.transmission;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import lombok.extern.slf4j.Slf4j;
import ming.data.transmission.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@Slf4j
@ConfigurationPropertiesScan
@SpringBootApplication
public class DataTransmissionApplication implements CommandLineRunner {

	@Autowired
	private ProductRepository productRepository;

	public static void main(String[] args) throws SQLException {
		SpringApplication.run(DataTransmissionApplication.class, args);
        // DataTransmissionApplication dta = new DataTransmissionApplication();
        // dta.connectDB();
	}

	public List<Product> fetchProduct() {
		return this.productRepository.queryProducts();
	}

	private void connectDB() throws SQLException {
		final String JDBC_URL = "jdbc:sqlserver://skywalker.local:1433;databaseName=testdb;user=sa;password=P@ssw0rd;";
		Connection conn = null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conn = DriverManager.getConnection(JDBC_URL);
			if(conn != null) {
				DatabaseMetaData metaObj = (DatabaseMetaData) conn.getMetaData();
				System.out.println("Driver Name?= " + metaObj.getDriverName() + ", Driver Version?= " + metaObj.getDriverVersion() + ", Product Name?= " + metaObj.getDatabaseProductName() + ", Product Version?= " + metaObj.getDatabaseProductVersion());
				Statement stmt = conn.createStatement();
				stmt.execute("INSERT INTO testdb.dbo.product_db\n" +
						"(id, name, price, category)\n" +
						"VALUES(122, 'pencil', 10.5, 0);");
				ResultSet rs = stmt.executeQuery("select * from product_db");
				List<Product> res = new ArrayList<>();
				while (rs.next()) {
				    res.add(Product.builder()
							.id(rs.getInt(1))
							.name(rs.getString(2))
							.price(rs.getDouble(3))
							.category(rs.getInt(4))
							.build());
				}
				System.out.println("res is:" + res);
			}
		} catch(Exception sqlException) {
			sqlException.printStackTrace();
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}

	@Override
	public void run(String... args) throws Exception {
	    List<Product> res = this.productRepository.queryProducts();
		log.info("products are: {}", res);
	}
}

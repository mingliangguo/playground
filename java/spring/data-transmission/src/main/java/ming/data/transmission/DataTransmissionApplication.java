package ming.data.transmission;

import java.sql.SQLException;
import java.util.List;
import java.util.Random;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
import ming.data.transmission.kafka.ProducerService;
import ming.data.transmission.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import ming.data.transmission.repository.*;

@Slf4j
@ConfigurationPropertiesScan
@SpringBootApplication
public class DataTransmissionApplication implements CommandLineRunner {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private ProducerService producerService;

	public static void main(String[] args) throws SQLException {
		SpringApplication.run(DataTransmissionApplication.class, args);
	}

	public List<Product> fetchProduct() {
		return productRepository.queryProducts();
	}

	public void sendMessage(Product p) {
		this.producerService.sendProduct("product", p);
	}

	@Override
	public void run(String... args) throws Exception {
		val rand = new Random();
		productRepository.createProduct(Product.builder()
				.id(rand.nextInt())
				.name(String.format("rand name %d", rand.nextInt(100)))
				.price(rand.nextDouble())
				.category(rand.nextInt(1000))
				.build()
		);
		val res = this.fetchProduct();
		res.forEach(this::sendMessage);
		log.info("products are: {}", res);
	}
}

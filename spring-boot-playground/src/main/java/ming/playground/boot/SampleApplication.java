package ming.playground.boot;

import com.ibm.cos.spring.framework.EnableCOS;
import lombok.Data;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableCOS
@SpringBootApplication
@EnableConfigurationProperties
@EntityScan(basePackages = "ming.demo.boot.model")
public class SampleApplication {
  @Data
  static class Config {
    String key;
    String value;
    Child child;

    @Data
    static class Child {
      String name;
      int age;
    }
  }

  @Configuration
  public static class DemoService {
    @Bean
    @ConfigurationProperties(prefix = "config1")
    public Config config1() {
      return new Config();
    }
    @Bean
    @ConfigurationProperties(prefix = "config2")
    public Config config2() {
      return new Config();
    }
  }

	public static void main(String[] args) {
		SpringApplication.run(SampleApplication.class, args);
	}

}

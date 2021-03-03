package ming.playground.boot;

import com.ibm.cos.spring.framework.EnableCOS;
import lombok.Data;
import ming.playground.boot.kafka.KafkaConstants;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import java.util.Map;

@EnableCOS
@SpringBootApplication
@EnableConfigurationProperties
@EntityScan(basePackages = "ming.playground.boot.model")
public class SampleApplication {
  @Configuration
  public static class AuthConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
      http.csrf().disable();
    }
  }

  @Data
  static class Config {
    String key;
    String value;
    Child child;
    Map<String, String> movieMapping;

    @Data
    static class Child {
      String name;
      int age;
    }
  }

  @Bean
  public NewTopic moviesTopic() {
    return new NewTopic(KafkaConstants.TOPIC_NAME, KafkaConstants.TOPIC_PARTITIONS, KafkaConstants.KAFKA_REPLICATION_FACTOR);
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

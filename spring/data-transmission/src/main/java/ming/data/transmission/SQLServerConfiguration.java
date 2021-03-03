package ming.data.transmission;

import java.sql.DriverManager;

import javax.sql.DataSource;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@EnableConfigurationProperties
@Configuration
public class SQLServerConfiguration {

    @Autowired
    private DataSourceConfig dbConfig;

    @NoArgsConstructor
    @Data
    @ConfigurationProperties(prefix = "spring.datasource.sqlserver")
    static class DataSourceConfig {
        String url;
        String userName;
        String password;
    }
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dmd = new DriverManagerDataSource();
        dmd.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        dmd.setUrl(dbConfig.getUrl());
        dmd.setUsername(dbConfig.getUserName());
        dmd.setPassword(dbConfig.getPassword());
        return dmd;
    }
}

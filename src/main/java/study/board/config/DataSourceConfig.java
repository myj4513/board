package study.board.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {
    @Bean
    DataSource dataSource() throws ClassNotFoundException {
        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
        Class driverClass = Class.forName("com.mysql.cj.jdbc.Driver");
        dataSource.setDriverClass(driverClass);
        dataSource.setUrl("jdbc:mysql://localhost:3307/board");
        dataSource.setUsername("root");
        dataSource.setPassword("111111");

        return dataSource;
    }

}

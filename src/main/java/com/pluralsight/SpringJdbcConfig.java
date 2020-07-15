package com.pluralsight;

import com.pluralsight.repository.RideRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.util.TimeZone;

@ComponentScan("com.pluralsight")
@Configuration
public class SpringJdbcConfig {
    @Autowired
    ApplicationContext context;

    @Bean
    @Primary
    public DataSource mysqlDataSource() {
/*        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/ride_tracker");
        dataSource.setUsername("application1");
        dataSource.setPassword("Application_Password123.");*/
        //return dataSource;
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("com.mysql.cj.jdbc.Driver");
        dataSourceBuilder.url("jdbc:mysql://localhost:3306/ride_tracker?serverTimezone=" + TimeZone.getDefault().getID());
        dataSourceBuilder.username("app_user");
        dataSourceBuilder.password("password");
        return dataSourceBuilder.build();
    }

    @Bean
    @Autowired
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(dataSource);
        return jdbcTemplate;
    }

    @Bean
    @Qualifier("MyRepositoryAlias")
    public RideRepository MyRepositoryAlias (@Value("${service.repositoryclass}") String qualifier) {
        return (RideRepository) context.getBean(qualifier);
    }
}
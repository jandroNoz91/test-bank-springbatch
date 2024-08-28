package com.example.bankBatch.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class ApplicationConfig {

    @Bean
    public DataSource dataSource(){
        DriverManagerDataSource driverManager = new DriverManagerDataSource();
        driverManager.setDriverClassName("com.mysql.cj.jdbc.Driver");
        driverManager.setUrl("jdbc:mysql://localhost:3306/account?createDatabaseIfNotExist=true&useSSL=false");
        driverManager.setUsername("root");
        driverManager.setPassword("Root*sa123456");

        return driverManager;
    }
}

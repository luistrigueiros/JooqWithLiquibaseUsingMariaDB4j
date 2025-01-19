package com.example.demo;

import ch.vorburger.exec.ManagedProcessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Slf4j
@Configuration
public class DataSourceConfig {
    private final MyMariaDB4jSpringService mariaDB4j;

    @Autowired
    public DataSourceConfig(MyMariaDB4jSpringService mariaDB4j) {
        this.mariaDB4j = mariaDB4j;
    }

    @Bean
    public DataSource dataSource() throws ManagedProcessException {
        String mydb = "mydb";
        mariaDB4j.createDb(mydb);
        String url = mariaDB4j.getConfiguration().getURL(mydb);
        log.info("DataSource URL: {}", url);
        return DataSourceBuilder.create()
                .driverClassName("org.mariadb.jdbc.Driver")
                .url(url)
                .username("root")
                .password("")
                .build();
    }
}

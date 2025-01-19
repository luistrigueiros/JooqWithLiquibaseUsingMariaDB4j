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
    private final DataSourceDetailsProvider detailsProvider;

    @Autowired
    public DataSourceConfig(DataSourceDetailsProvider detailsProvider) {
        this.detailsProvider = detailsProvider;
    }

    @Bean
    public DataSource dataSource() throws ManagedProcessException {
        String url = detailsProvider.getJdbcUrl();
        log.info("DataSource URL: {}", url);
        return DataSourceBuilder.create()
                .driverClassName(detailsProvider.getDriverClassName())
                .url(url)
                .username(detailsProvider.getDbUser())
                .password(detailsProvider.getDbPassword())
                .build();
    }

}

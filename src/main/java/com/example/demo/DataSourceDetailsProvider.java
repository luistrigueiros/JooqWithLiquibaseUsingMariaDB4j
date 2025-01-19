package com.example.demo;

import ch.vorburger.exec.ManagedProcessException;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DataSourceDetailsProvider {
    private final MyMariaDB4jSpringService mariaDB4j;
    private final String mydb = "mydb";
    private final String user = "root";

    @Autowired
    public DataSourceDetailsProvider(MyMariaDB4jSpringService mariaDB4j) {
        this.mariaDB4j = mariaDB4j;
    }

    public String getJdbcUrl() {
        return mariaDB4j.getConfiguration().getURL(mydb);
    }

    public String getDriverClassName() {
        return "org.mariadb.jdbc.Driver";
    }

    public String getDbName() {
        return mydb;
    }
    public String getDbUser() {
        return user;
    }
    public String getDbPassword() {
        return "";
    }

    @PostConstruct
    void init() throws ManagedProcessException {
        mariaDB4j.createDb(mydb);
    }
}

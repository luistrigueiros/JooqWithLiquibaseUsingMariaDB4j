package com.example.demo;

import ch.vorburger.exec.ManagedProcessException;
import org.springframework.stereotype.Component;

@Component
public class MyMariaDB4jSpringService extends ch.vorburger.mariadb4j.springframework.MariaDB4jSpringService {
    public void createDb(String dbName) throws ManagedProcessException {
        getDB().createDB(dbName);
    }

}

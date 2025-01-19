package com.example.demo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jooq.codegen.GenerationTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@Slf4j
@SpringBootApplication
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class JooqWithLiquibaseUsingMariaDb4jApplication implements CommandLineRunner {
    private final GeneratorConfig generatorConfig;
    private final ConfigurableApplicationContext applicationContext;

    public static void main(String[] args) {
        SpringApplication.run(JooqWithLiquibaseUsingMariaDb4jApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("About to perform code generation");
        GenerationTool.generate(generatorConfig.createConfiguration());
        log.info("Application stopped");
        applicationContext.stop();
        applicationContext.close();
    }
}

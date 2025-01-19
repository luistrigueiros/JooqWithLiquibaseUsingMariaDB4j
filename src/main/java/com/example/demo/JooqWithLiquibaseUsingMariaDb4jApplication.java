package com.example.demo;

import ch.vorburger.mariadb4j.MariaDB4jService;
import ch.vorburger.mariadb4j.springframework.MariaDB4jSpringService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jooq.codegen.GenerationTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;

@Slf4j
@SpringBootApplication
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class JooqWithLiquibaseUsingMariaDb4jApplication implements ExitCodeGenerator, CommandLineRunner {
    private final MariaDB4jSpringService mariaDB4j;
    private final GeneratorConfig generatorConfig;

    public static void main(String[] args) throws IOException {
        SpringApplication app = new SpringApplication(JooqWithLiquibaseUsingMariaDb4jApplication.class);
        app.setBannerMode(Banner.Mode.OFF);
        ConfigurableApplicationContext ctx = app.run(args);
        log.info("Application started");
        MariaDB4jService.waitForKeyPressToCleanlyExit();
        log.info("Application stopped");
        ctx.stop();
        ctx.close();
    }

    @Override
    public int getExitCode() {
        return mariaDB4j.getLastException() == null ? 0 : -1;
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("About to perform code generation");
        GenerationTool.generate(generatorConfig.createConfiguration());
    }
}

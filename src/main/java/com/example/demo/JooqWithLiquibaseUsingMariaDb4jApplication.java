package com.example.demo;

import ch.vorburger.mariadb4j.MariaDB4jService;
import ch.vorburger.mariadb4j.springframework.MariaDB4jSpringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;

@SpringBootApplication
public class JooqWithLiquibaseUsingMariaDb4jApplication implements ExitCodeGenerator {
	private final MariaDB4jSpringService mariaDB4j;

	@Autowired
    public JooqWithLiquibaseUsingMariaDb4jApplication(MariaDB4jSpringService mariaDB4j) {
        this.mariaDB4j = mariaDB4j;
    }

    public static void main(String[] args) throws IOException {
		SpringApplication app = new SpringApplication(JooqWithLiquibaseUsingMariaDb4jApplication.class);
		app.setBannerMode(Banner.Mode.OFF);
		ConfigurableApplicationContext ctx = app.run(args);

		MariaDB4jService.waitForKeyPressToCleanlyExit();

		ctx.stop();
		ctx.close();
	}

	@Override
	public int getExitCode() {
		return mariaDB4j.getLastException() == null ? 0 : -1;
	}

}

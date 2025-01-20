
# JooqWithLiquibaseUsingMariaDB4j

This example showcases how to integrate JOOQ with Liquibase in a SpringBoot application using MariaDB4j. 

This allows to perform migrations and code generation without having to install install MariaDB4j or use containers.

The database is installed and created on the fly on a temporary folder and deleted and the end of the code geneation.

This is using programatic configuration of JOOQ as is allows to more easly pass into it the MariaDB4j url that is dynamic and generated on the fly.

[GeneratorConfig](src/main/java/com/example/demo/GeneratorConfig.java)


# References:

- [MariaDB4j](https://github.com/MariaDB4j/MariaDB4j)
- [JOOQ Liquibase codegeneration](https://www.jooq.org/doc/latest/manual/code-generation/codegen-liquibase/)

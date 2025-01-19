package com.example.demo;

//import org.jooq.codegen.Generator;

import org.jooq.meta.jaxb.*;

import org.jooq.meta.jaxb.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

@org.springframework.context.annotation.Configuration
public class GeneratorConfig {
    private final DataSourceDetailsProvider detailsProvider;

    @Autowired
    public GeneratorConfig(DataSourceDetailsProvider detailsProvider) {
        this.detailsProvider = detailsProvider;
    }

    @Bean
    public Configuration createConfiguration() {
        var target = new Target()
                .withPackageName("org.jooq.your.packagename")
                .withDirectory("build/jooq-generated");
        var database = new Database()
                .withName("org.jooq.meta.mariadb.MariaDBDatabase")
                .withIncludes(".*")
                .withExcludes("databasechangelog|databasechangeloglock")
                .withInputSchema(detailsProvider.getDbName());
        var generator1 = new org.jooq.meta.jaxb.Generator()
                .withDatabase(database)
                // Generation flags: See advanced configuration properties
                //.withGenerate( new Generator().withName("org.jooq.codegen.JavaGenerator"))
                .withTarget(target);
        var root = new Jdbc()
                .withDriver(detailsProvider.getDriverClassName())
                .withUrl(detailsProvider.getJdbcUrl())
                .withUser("root");
        Configuration configuration = new Configuration()
                .withJdbc(root)
                .withGenerator(generator1);
//                .withGenerator(new Generate()
//
//                        // Possible values for generatedAnnotationType
//                        // - DETECT_FROM_JDK
//                        // - JAVAX_ANNOTATION_GENERATED
//                        // - JAVAX_ANNOTATION_PROCESSING_GENERATED
//                        // - ORG_JOOQ_GENERATED
//                        .withGeneratedAnnotation(true)
//                        .withGeneratedAnnotationType(GeneratedAnnotationType.DETECT_FROM_JDK)
//                        .withGeneratedAnnotationDate(true)
//                        .withGeneratedAnnotationJooqVersion(true)
//                        .withNullableAnnotation(true)
//                        //.withNullableAnnotationOnWriteOnlyNullableTypes(true)
//                        .withNullableAnnotationType("javax.annotation.Nullable")
//                        .withNonnullAnnotation(true)
//                        .withNonnullAnnotationType("javax.annotation.Nonnull")
//                        //.withJpaAnnotations(true)
//                        //.withJpaVersion(2.2)
//                        .withValidationAnnotations(true)
//
//                        // The springDao flag enables the generation of @Transactional annotations on a
//                        // generated, Spring-specific DAO
//                        .withSpringAnnotations(true)
//                        .withSpringDao(true)
//                        .withKotlinSetterJvmNameAnnotationsOnIsPrefix(true)
//                        .withConstructorPropertiesAnnotation(true)
//                        .withConstructorPropertiesAnnotationOnPojos(true)
//                        .withConstructorPropertiesAnnotationOnRecords(true));
        return configuration;
    }
}

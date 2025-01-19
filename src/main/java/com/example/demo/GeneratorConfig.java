package com.example.demo;


import org.jooq.meta.jaxb.*;
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
//                        .withValidationAnnotations(true)
//
//                        // The springDao flag enables the generation of @Transactional annotations on a
//                        // generated, Spring-specific DAO
//                        .withConstructorPropertiesAnnotation(true)
//                        .withConstructorPropertiesAnnotationOnPojos(true)
//                        .withConstructorPropertiesAnnotationOnRecords(true));
        return new Configuration()
                .withJdbc(new Jdbc()
                        .withDriver(detailsProvider.getDriverClassName())
                        .withUrl(detailsProvider.getJdbcUrl())
                        .withUser(detailsProvider.getDbUser())
                        .withPassword(detailsProvider.getDbPassword()))
                .withGenerator(new Generator()
                        .withDatabase(new Database()
                                .withName("org.jooq.meta.mariadb.MariaDBDatabase")
                                .withIncludes(".*")
                                .withExcludes("databasechangelog|databasechangeloglock")
                                .withInputSchema(detailsProvider.getDbName()))
                        .withGenerate(new Generate()
                                .withPojos(true)
                                .withSpringAnnotations(true)
                                .withDaos(true)
                                .withValidationAnnotations(true)
                        )
                        .withTarget(new Target()
                                .withPackageName("org.jooq.your.packagename")
                                .withDirectory("build/jooq-generated")));
    }
}

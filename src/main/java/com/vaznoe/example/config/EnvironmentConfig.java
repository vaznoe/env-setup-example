package com.vaznoe.example.config;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.util.List;
import java.util.Optional;

@Configuration
@ComponentScan(basePackages = "com.vaznoe.example")
public class EnvironmentConfig {

    private static final Logger logger = LoggerFactory.getLogger(EnvironmentConfig.class);
    private static final String ENVIRONMENT_NAME = "env";
    private static final String DEFAULT_ENVIRONMENT = "LOCAL";
    private static final List<String> VALID_ENVS = ImmutableList.<String>builder().add("LOCAL").add("QA").add("UAT").build();

    @Bean
    public static EnvironmentPropertyPlaceholderConfigurer propertyPlaceholderConfigurer() {
        String env = Optional.ofNullable(System.getProperty(ENVIRONMENT_NAME)).orElse(DEFAULT_ENVIRONMENT);
        logger.info("Provided environment: {}", env);
        Preconditions.checkArgument(VALID_ENVS.contains(env), "Incorrect environment provided %s", env);
        EnvironmentPropertyPlaceholderConfigurer configurer = new EnvironmentPropertyPlaceholderConfigurer(env);
        configurer.setIgnoreUnresolvablePlaceholders(true);
        configurer.setNullValue("@null");
        configurer.setLocation(new ClassPathResource("application.properties"));
        return configurer;
    }
}
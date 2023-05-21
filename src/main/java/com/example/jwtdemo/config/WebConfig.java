package com.example.jwtdemo.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.zalando.problem.jackson.ProblemModule;
import org.zalando.problem.violations.ConstraintViolationProblemModule;

import java.util.Locale;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder(8);
    }

    @Bean
    public LocaleResolver localeResolver(Locale locale) {
        SessionLocaleResolver resolver = new SessionLocaleResolver();
        resolver.setDefaultLocale(locale);
        return resolver;
    }

    @Bean
    public Locale locale() {
        Locale.setDefault(Locale.ENGLISH);
        return Locale.getDefault();
    }

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer objectMapper() {
        return builder -> builder
                .modulesToInstall(ProblemModule.class, ConstraintViolationProblemModule.class)
                .serializationInclusion(JsonInclude.Include.NON_NULL)
                .featuresToDisable(SerializationFeature.FAIL_ON_EMPTY_BEANS,
                        SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                .featuresToEnable(SerializationFeature.WRITE_DATES_WITH_ZONE_ID,
                        MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS,
                        DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    }
}

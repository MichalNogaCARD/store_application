package com.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class PrimaPlatformaApplication extends SpringBootServletInitializer {

    private final static Logger LOGGER = LoggerFactory.getLogger(PrimaPlatformaApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(PrimaPlatformaApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(PrimaPlatformaApplication.class);
    }
}
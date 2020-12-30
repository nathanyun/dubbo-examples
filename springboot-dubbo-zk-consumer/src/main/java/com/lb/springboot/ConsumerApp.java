package com.lb.springboot;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * Hello world!
 */
@SpringBootApplication
public class ConsumerApp {

    public static void main(String[] args) {
        new SpringApplicationBuilder(ConsumerApp.class)
                .web(WebApplicationType.SERVLET)
                .run(args);
    }
}

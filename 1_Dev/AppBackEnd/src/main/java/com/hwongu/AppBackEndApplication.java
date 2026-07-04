package com.hwongu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;


@SpringBootApplication
public class AppBackEndApplication  extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(AppBackEndApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(AppBackEndApplication.class);
    }
}

//Para ejecutar como JAR de manera local
/*
@SpringBootApplication
public class AppBackEndApplication {

    public static void main(String[] args) {
        SpringApplication.run(AppBackEndApplication.class, args);
    }

}*/

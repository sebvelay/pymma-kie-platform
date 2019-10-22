package org.chtijbug.drools.console;


import org.chtijbug.drools.console.service.util.ApplicationContextProvider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.*;

import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@SpringBootApplication
@ComponentScan({"org.chtijbug.drools.proxy.persistence"})
@EnableMongoRepositories("org.chtijbug.drools.proxy.persistence.repository")

@PropertySource("classpath:application.properties")
@ImportResource("classpath:applicationContext.xml")
public class DroolsSpringBootConsoleApplication extends SpringBootServletInitializer {

    @Bean
    public ApplicationContextProvider getAppplicationContext() {
        ApplicationContextProvider applicationContextProvider = new ApplicationContextProvider();
        return applicationContextProvider;
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(DroolsSpringBootConsoleApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(DroolsSpringBootConsoleApplication.class, args);
    }



}

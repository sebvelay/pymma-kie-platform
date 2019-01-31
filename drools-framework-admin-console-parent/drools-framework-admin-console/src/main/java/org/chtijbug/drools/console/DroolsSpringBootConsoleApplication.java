package org.chtijbug.drools.console;


import org.chtijbug.drools.console.service.util.ApplicationContextProvider;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.*;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Repository;

@Configuration
@SpringBootApplication
@ComponentScan({"org.chtijbug.drools.proxy.persistence","org.chtijbug.drools.indexer"})
@EnableMongoRepositories("org.chtijbug.drools.proxy.persistence.repository")
@EnableElasticsearchRepositories(basePackages = "org.chtijbug.drools.indexer.persistence.repository")
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

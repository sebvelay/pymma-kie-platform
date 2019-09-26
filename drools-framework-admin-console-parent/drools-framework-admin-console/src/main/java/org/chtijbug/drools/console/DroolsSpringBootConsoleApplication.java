package org.chtijbug.drools.console;


import org.chtijbug.drools.console.service.KeyCloackService;
import org.chtijbug.drools.console.service.util.ApplicationContextProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.*;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@SpringBootApplication
@ComponentScan({"org.chtijbug.drools.proxy.persistence","org.chtijbug.drools.indexer"})
@EnableMongoRepositories("org.chtijbug.drools.proxy.persistence.repository")
@EnableElasticsearchRepositories(basePackages = "org.chtijbug.drools.indexer.persistence.repository")
@PropertySource("classpath:application.properties")
@ImportResource("classpath:applicationContext.xml")
public class DroolsSpringBootConsoleApplication extends SpringBootServletInitializer {


    @Value("${kie.platform.keycloack.url}")
    private String serverUrl;

    @Value("${kie.platform.keycloack.realm}")
    private String serverRealm;

    @Value("${kie.platform.keycloack.username}")
    private String serverUserName;

    @Value("${kie.platform.keycloack.password}")
    private String serverPassword;

    @Value("${kie.platform.keycloack.clientId}")
    private String serverClientId;


    //@Bean
    public KeyCloackService initKeyCloack(){
        return new KeyCloackService( serverUrl,  serverRealm,  serverUserName,  serverPassword,  serverClientId);
    }

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

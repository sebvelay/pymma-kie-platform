package org.chtijbug.drools.console;


import org.chtijbug.drools.console.middle.DababaseContentInit;
import org.chtijbug.drools.console.service.model.kie.KieConfigurationData;
import org.chtijbug.drools.console.service.util.ApplicationContextProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.*;
import org.springframework.context.event.EventListener;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@SpringBootApplication
@EnableMongoRepositories("org.chtijbug.drools.proxy.persistence.repository")

@PropertySource("classpath:application.properties")
public class DroolsSpringBootConsoleApplication extends SpringBootServletInitializer {

    @Value("${kie-wb.baseurl}")
    private String kiewbUrl;

    @Autowired
    private DababaseContentInit dababaseContentInit;

    @Bean(name = "applicationContext")
    public ApplicationContextProvider getAppplicationContext() {
        ApplicationContextProvider applicationContextProvider = new ApplicationContextProvider();
        return applicationContextProvider;
    }

    @Bean
    public KieConfigurationData createKieConfigurationData(){
        KieConfigurationData kieConfigurationData = new KieConfigurationData();
        kieConfigurationData.setKiewbUrl(kiewbUrl);
        return kieConfigurationData;
    }
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(DroolsSpringBootConsoleApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(DroolsSpringBootConsoleApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void InitPlatform(){
        dababaseContentInit.initDatabaseIfNecessary();
    }


}

package org.chtijbug.drools.console;


import com.vaadin.flow.spring.SpringServlet;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.chtijbug.drools.KieContainerResponse;
import org.chtijbug.drools.KieContainerUpdate;
import org.chtijbug.drools.ReverseProxyUpdate;
import org.chtijbug.drools.common.KafkaTopicConstants;
import org.chtijbug.drools.console.middle.DababaseContentInit;
import org.chtijbug.drools.console.service.model.kie.KieConfigurationData;
import org.chtijbug.drools.console.service.util.ApplicationContextProvider;
import org.chtijbug.drools.console.service.wbconnector.KieBusinessCentralConnector;
import org.chtijbug.drools.proxy.persistence.repository.KieWorkbenchRepository;
import org.chtijbug.drools.proxy.persistence.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.event.EventListener;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.*;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.HashMap;
import java.util.Map;

@Configuration
@SpringBootApplication
@EnableMongoRepositories("org.chtijbug.drools.proxy.persistence.repository")
@EnableKafka
@PropertySource("classpath:application.properties")
public class DroolsSpringBootConsoleApplication extends SpringBootServletInitializer {

    @Value("${kie-wb.baseurl}")
    private String kiewbUrl;

    @Value(value = "${kafka.bootstrapAddress}")
    private String bootstrapAddress;

    @Autowired
    private DababaseContentInit dababaseContentInit;
    @Autowired
    private KieBusinessCentralConnector kieBusinessCentralConnector;
    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private KieWorkbenchRepository kieWorkbenchRepository;

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                super.addCorsMappings(registry);
                registry.addMapping("/**")
                        .allowedOrigins("*")
                        .allowedMethods("GET", "POST", "OPTIONS", "DELETE", "PUT")
                        .allowedHeaders("Access-Control-Allow-Origin", "*")
                        .exposedHeaders("Access-Token", "Access-Control-Allow-Origin")
                        .allowCredentials(false).maxAge(3600);
            }
        };
    }


    @Bean(name = "applicationContext")
    public ApplicationContextProvider getAppplicationContext() {
        ApplicationContextProvider applicationContextProvider = new ApplicationContextProvider();
        return applicationContextProvider;
    }

    @Bean
    public KieConfigurationData createKieConfigurationData(){
        KieConfigurationData kieConfigurationData = new KieConfigurationData();
        kieConfigurationData.setKiewbUrl(kiewbUrl);
        kieConfigurationData.setName("demo");
        return kieConfigurationData;
    }
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(DroolsSpringBootConsoleApplication.class);
    }


    @Bean
    public KafkaAdmin kafkaAdmin() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        return new KafkaAdmin(configs);
    }

    @Bean
    public NewTopic loggingTopic() {
        return new NewTopic(KafkaTopicConstants.REVERSE_PROXY, 1, (short) 1);
    }

    @Bean
    public ProducerFactory<String, ReverseProxyUpdate> producerFactory() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(
                ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
                bootstrapAddress);
        configProps.put(
                ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
                StringSerializer.class);
        configProps.put(
                ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                JsonSerializer.class);
        return new DefaultKafkaProducerFactory<>(configProps);
    }
    @Bean
    public ProducerFactory<String, KieContainerUpdate> producerKieContainerUpdateFactory() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(
                ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
                bootstrapAddress);
        configProps.put(
                ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
                StringSerializer.class);
        configProps.put(
                ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                JsonSerializer.class);
        return new DefaultKafkaProducerFactory<>(configProps);
    }

    @Bean
    public KafkaTemplate<String, ReverseProxyUpdate> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }

    @Bean
    public KafkaTemplate<String, KieContainerUpdate> kafkaKieContainerUpdateTemplate() {
        return new KafkaTemplate<>(producerKieContainerUpdateFactory());
    }


    public ConsumerFactory<String, KieContainerResponse> greetingConsumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        props.put(ConsumerConfig.GROUP_ID_CONFIG,"Console");
        return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(), new JsonDeserializer<>(KieContainerResponse.class));
    }
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, KieContainerResponse>
    ruleKafkaListenerKieContainerUpdateFactory() {

        ConcurrentKafkaListenerContainerFactory<String, KieContainerResponse> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(greetingConsumerFactory());
        return factory;
    }

    @Bean
    public ServletRegistrationBean<SpringServlet> springServlet(ApplicationContext context) {
        return new ServletRegistrationBean<>(new SpringServlet(context), "/admin/*", "/frontend/*");
    }


    public static void main(String[] args) {
        SpringApplication.run(DroolsSpringBootConsoleApplication.class, args);
    }


   @EventListener(ApplicationReadyEvent.class)
    public void InitPlatform(){
        dababaseContentInit.initDatabaseIfNecessary();
        /**
       for (KieWorkbench kieWorkbench: kieWorkbenchRepository.findAll()) {
           Map<String, KieContainerResource> kies = new HashMap<>();
           KieServerSetup kieServerSetup = kieBusinessCentralConnector.connectToBusinessCentral("nheron", "adminnheron00@", kieWorkbench.getName(),kieWorkbench.getExternalUrl());
           if (kieServerSetup != null && kieServerSetup.getContainers() != null) {
               for (KieContainerResource kieContainerResource : kieServerSetup.getContainers()) {
                   kies.put(kieContainerResource.getContainerId(), kieContainerResource);
               }
           }
           List<ProjectPersist> projectRepositories = projectRepository.findByKieWorkbench(kieWorkbench);
           if (!projectRepositories.isEmpty()){
               for (ProjectPersist projectPersist : projectRepository.findAll()) {
                   if (projectPersist.getServerNames().size() > 0) {
                       if (!kies.containsKey(projectPersist.getArtifactID()+"_"+projectPersist.getProjectVersion())) {
                           kieBusinessCentralConnector.createContainer("nheron", "adminnheron00@", projectPersist,kieWorkbench.getExternalUrl());
                       } else {
                           kieBusinessCentralConnector.updateContainer("nheron", "adminnheron00@", projectPersist, kies.get(projectPersist.getArtifactID()+"_"+projectPersist.getProjectVersion()),kieWorkbench.getExternalUrl());
                       }
                   }
               }
           }
           /**
               for (ProjectPersist projectPersist : projectRepository.findAll()) {
                   if (projectPersist.getServerNames().size() > 0) {
                       if (!kies.containsKey(projectPersist.getContainerID())) {
                           kieBusinessCentralConnector.createContainer("nheron", "adminnheron00@", projectPersist);
                       } else {
                           kieBusinessCentralConnector.updateContainer("nheron", "adminnheron00@", projectPersist, kies.get(projectPersist.getContainerID()));
                       }
                   }
               }

               ServerInstanceKeyList serverInstanceKeyList = kieBusinessCentralConnector.getListInstances("nheron", "adminnheron00@");
               if (serverInstanceKeyList != null) {
                   for (ServerInstanceKey serverInstanceKey : serverInstanceKeyList.getServerInstanceKeys()) {
                       String serverInstanceId = serverInstanceKey.getServerInstanceId();
                       ContainerList containerList = kieBusinessCentralConnector.getListContainers("nheron", "adminnheron00@", serverInstanceId);
                       System.out.println("coucou");
                       for (Container container : containerList.getContainers()) {

                       }
                   }
                   System.out.println("coucou");
               }
               System.out.println("coucou");

       }
         **/
    }


}

package org.chtijbug.drools.console;


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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
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


    public static void main(String[] args) {
        SpringApplication.run(DroolsSpringBootConsoleApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void InitPlatform(){
        dababaseContentInit.initDatabaseIfNecessary();
    }


}

package org.chtijbug.drools.reverseproxy;


import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.chtijbug.drools.ReverseProxyUpdate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@EnableMongoRepositories(basePackages = "org.chtijbug.drools.proxy.persistence.repository")
public class DroolsBusinessReverseProxyServer {

    @Value(value = "${kafka.bootstrapAddress}")
    private String bootstrapAddress;

    public ConsumerFactory<String, ReverseProxyUpdate> mappingConsumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "greeting");
        return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(), new JsonDeserializer<>(ReverseProxyUpdate.class));
    }
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, ReverseProxyUpdate>
    mappingKafkaListenerContainerFactory() {

        ConcurrentKafkaListenerContainerFactory<String, ReverseProxyUpdate> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(mappingConsumerFactory());
        return factory;
    }

    public static void main(String[] args) {
        SpringApplication.run(DroolsBusinessReverseProxyServer.class, args);
    }

}

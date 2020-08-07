/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.chtijbug.drools.proxy;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.chtijbug.drools.ChtijbugObjectRequest;
import org.chtijbug.drools.KieContainerResponse;
import org.chtijbug.drools.KieContainerUpdate;
import org.chtijbug.drools.common.KafkaTopicConstants;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.*;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.springframework.kafka.transaction.KafkaTransactionManager;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

// CHECKSTYLE:OFF
@SpringBootApplication
@Configuration
@EnableKafka
public class DroolsBusinessProxyServer {

    @Value(value = "${org.kie.server.id}")
    private String groupID;

    @Value(value = "${kafka.bootstrapAddress}")
    private String bootstrapAddress;

    @Bean
    public KafkaAdmin kafkaAdmin() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        return new KafkaAdmin(configs);
    }

    @Bean
    public NewTopic loggingTopic() {
        return new NewTopic(KafkaTopicConstants.LOGING_TOPIC, 1, (short) 1);
    }

    @Bean
    public NewTopic actionResponseTopic() {
        return new NewTopic(KafkaTopicConstants.RESPONSE_TOPIC, 1, (short) 1);
    }

    @Bean
    public ProducerFactory<String, ChtijbugObjectRequest> producerFactory() {
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
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        new DefaultKafkaProducerFactory<>(configProps, new StringSerializer(), new JsonSerializer<ChtijbugObjectRequest>(objectMapper));
        return new DefaultKafkaProducerFactory<>(configProps);
    }
    @Bean
    public ProducerFactory<String, KieContainerResponse> producerKieContainerResponseactory() {
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
        DefaultKafkaProducerFactory<String, KieContainerResponse> producer = new DefaultKafkaProducerFactory<>(configProps);
        producer.transactionCapable();
        producer.setTransactionIdPrefix("trans");
        return producer;
    }
    @Bean
    public KafkaTransactionManager transactionManager(ProducerFactory producerFactory) {
        KafkaTransactionManager manager = new KafkaTransactionManager(producerKieContainerResponseactory());
        return manager;
    }
    @Bean
    public KafkaTemplate<String, KieContainerResponse> kafkaKieContainerUpdateResponsableTemplate() {
        return new KafkaTemplate<>(producerKieContainerResponseactory());
    }
    @Bean(name="deployFinish")
    public NewTopic actionDeployResponseTopic() {
        return new NewTopic(KafkaTopicConstants.RESPONSE_DEPLOY_TOPIC, 1, (short) 1);
    }


    public ConsumerFactory<String, KieContainerUpdate> greetingConsumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, groupID);
        return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(), new JsonDeserializer<>(KieContainerUpdate.class));
    }
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, KieContainerUpdate>
    ruleKafkaListenerKieContainerUpdateFactory() {

        ConcurrentKafkaListenerContainerFactory<String, KieContainerUpdate> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(greetingConsumerFactory());
        return factory;
    }

    @Bean
    public KafkaTemplate<String, ChtijbugObjectRequest> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }





    /**
     * Main method to start the application.
     */
    public static void main(String[] args) {
        if (System.getProperty("org.kie.server.id") == null) {
            InetAddress inetAddress = null;
            try {
                inetAddress = InetAddress.getLocalHost();
                String hostName = inetAddress.getHostName();
                System.setProperty("org.kie.server.id", hostName);
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
        }
        SpringApplication.run(DroolsBusinessProxyServer.class, args);
    }

}
// CHECKSTYLE:ON

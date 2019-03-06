package org.chtijbug.drools.reverseproxy;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories(basePackages = "org.chtijbug.drools.proxy.persistence.repository")
public class DroolsBusinessReverseProxyServer {

        public static void main(String[] args) {
            SpringApplication.run(DroolsBusinessReverseProxyServer.class, args);
        }

}

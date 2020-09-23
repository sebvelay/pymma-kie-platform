package org.chtijbug.drools.console;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@EnableReactiveMongoRepositories
public class MongoReactiveApplication
        extends AbstractReactiveMongoConfiguration {

    @Value(value = "${spring.data.mongodb.database")
    private String databaseName;



    @Override
    protected String getDatabaseName() {
        return databaseName;
    }

    @Override
    public com.mongodb.reactivestreams.client.MongoClient reactiveMongoClient() {
        return com.mongodb.reactivestreams.client.MongoClients.create(databaseName);
    }
}

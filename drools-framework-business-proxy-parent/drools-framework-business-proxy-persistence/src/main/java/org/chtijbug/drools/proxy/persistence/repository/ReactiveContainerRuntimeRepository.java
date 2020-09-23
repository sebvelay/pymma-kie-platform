package org.chtijbug.drools.proxy.persistence.repository;

import org.chtijbug.drools.proxy.persistence.model.ContainerRuntimePojoPersist;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.mongodb.repository.Tailable;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface ReactiveContainerRuntimeRepository extends ReactiveMongoRepository<ContainerRuntimePojoPersist, String> {

    @Tailable
    Flux<ContainerRuntimePojoPersist>  findByServerNameAndHostname(String serverName, String hostName);





}

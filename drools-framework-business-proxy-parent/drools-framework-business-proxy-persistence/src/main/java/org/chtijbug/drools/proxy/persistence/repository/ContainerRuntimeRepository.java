package org.chtijbug.drools.proxy.persistence.repository;

import org.chtijbug.drools.proxy.persistence.model.ContainerRuntimePojoPersist;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContainerRuntimeRepository extends MongoRepository<ContainerRuntimePojoPersist, String> {


    List<ContainerRuntimePojoPersist> findByServerNameAndContainerId(String serverName, String containerId);
    List<ContainerRuntimePojoPersist> findByServerNameAndStatus(String serverName, String status);
    List<ContainerRuntimePojoPersist> findByServerNameAndHostname(String serverName, String hostname);
    ContainerRuntimePojoPersist findByServerNameAndContainerIdAndHostname(String serverName, String containerId,String hostname);




}

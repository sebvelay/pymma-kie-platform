package org.chtijbug.drools.proxy.persistence;

import org.chtijbug.drools.proxy.persistence.model.ContainerPojoPersist;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContainerRepository extends MongoRepository<ContainerPojoPersist, String> {


    ContainerPojoPersist findByServerNameAndContainerId(String serverName,String containerId);

    List<ContainerPojoPersist> findByContainerId(String containerId);

    List<ContainerPojoPersist> findByServerName(String serverName);


}

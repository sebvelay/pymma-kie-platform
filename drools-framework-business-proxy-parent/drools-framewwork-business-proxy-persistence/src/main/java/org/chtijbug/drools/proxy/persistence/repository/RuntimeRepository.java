package org.chtijbug.drools.proxy.persistence.repository;

import org.chtijbug.drools.proxy.persistence.model.RuntimePersist;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RuntimeRepository extends MongoRepository<RuntimePersist, String> {

    public List<RuntimePersist> findByServerName(String serverName);
}

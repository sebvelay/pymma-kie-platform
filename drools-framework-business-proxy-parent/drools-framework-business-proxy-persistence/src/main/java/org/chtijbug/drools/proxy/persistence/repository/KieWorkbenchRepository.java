package org.chtijbug.drools.proxy.persistence.repository;

import org.chtijbug.drools.proxy.persistence.model.KieWorkbench;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KieWorkbenchRepository extends MongoRepository<KieWorkbench, String> {

    KieWorkbench findByName(String name);
    KieWorkbench findByID(String ID);
}

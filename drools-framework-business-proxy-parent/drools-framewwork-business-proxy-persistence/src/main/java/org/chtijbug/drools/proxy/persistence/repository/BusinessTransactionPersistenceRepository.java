package org.chtijbug.drools.proxy.persistence.repository;


import org.chtijbug.drools.proxy.persistence.model.BusinessTransactionPersistence;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface BusinessTransactionPersistenceRepository extends MongoRepository<BusinessTransactionPersistence,String> {

    public BusinessTransactionPersistence findAllById(String id, PageRequest pageRequest);

    public List<BusinessTransactionPersistence> findAllByTransactionId(String transactionId, PageRequest pageRequest);

}


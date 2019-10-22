package org.chtijbug.drools.proxy.persistence.repository;


import org.chtijbug.drools.proxy.persistence.model.BusinessTransactionAction;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface BusinessTransactionActionRepository extends MongoRepository<BusinessTransactionAction,String> {

    public List<BusinessTransactionAction> findAllByBusinessTransactionId(String businessTransactionId, Sort sort, PageRequest pageRequest);

}

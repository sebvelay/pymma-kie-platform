package org.chtijbug.drools.indexer.persistence.repository;

import org.chtijbug.drools.indexer.persistence.model.BusinessTransactionAction;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface BusinessTransactionActionRepository extends ElasticsearchRepository<BusinessTransactionAction,String> {

    public List<BusinessTransactionAction> findByBusinessTransactionId(String businessTransactionId);

}

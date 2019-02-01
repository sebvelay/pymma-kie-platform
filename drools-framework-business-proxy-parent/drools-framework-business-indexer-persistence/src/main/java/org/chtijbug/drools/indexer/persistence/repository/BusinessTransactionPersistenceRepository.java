package org.chtijbug.drools.indexer.persistence.repository;

import org.chtijbug.drools.indexer.persistence.model.BusinessTransactionPersistence;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface BusinessTransactionPersistenceRepository extends ElasticsearchRepository<BusinessTransactionPersistence,String> {

    public BusinessTransactionPersistence findAllById(String id, PageRequest pageRequest);

    public List<BusinessTransactionPersistence> findAllByTransactionId(String transactionId, PageRequest pageRequest);

}


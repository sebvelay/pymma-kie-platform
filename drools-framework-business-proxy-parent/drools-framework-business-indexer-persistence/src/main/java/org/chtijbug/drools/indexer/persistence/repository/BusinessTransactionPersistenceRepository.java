package org.chtijbug.drools.indexer.persistence.repository;

import org.chtijbug.drools.indexer.persistence.model.BusinessTransactionPersistence;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface BusinessTransactionPersistenceRepository extends ElasticsearchRepository<BusinessTransactionPersistence,String> {


}

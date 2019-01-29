package org.chtijbug.drools.indexer.persistence.repository;

import org.chtijbug.drools.indexer.persistence.model.BusinessTransactionAction;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface BusinessTransactionActionRepository extends ElasticsearchRepository<BusinessTransactionAction,String> {


}

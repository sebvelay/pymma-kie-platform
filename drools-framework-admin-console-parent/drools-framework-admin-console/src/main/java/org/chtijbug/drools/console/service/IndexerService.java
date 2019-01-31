package org.chtijbug.drools.console.service;

import org.chtijbug.drools.indexer.persistence.repository.BusinessTransactionActionRepository;
import org.chtijbug.drools.indexer.persistence.repository.BusinessTransactionPersistenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IndexerService {


    @Autowired
    private BusinessTransactionPersistenceRepository businessTransactionPersistenceRepository;

    @Autowired
    private BusinessTransactionActionRepository businessTransactionActionRepository;

    public BusinessTransactionPersistenceRepository getBusinessTransactionPersistenceRepository() {
        return businessTransactionPersistenceRepository;
    }

    public void setBusinessTransactionPersistenceRepository(BusinessTransactionPersistenceRepository businessTransactionPersistenceRepository) {
        this.businessTransactionPersistenceRepository = businessTransactionPersistenceRepository;
    }

    public BusinessTransactionActionRepository getBusinessTransactionActionRepository() {
        return businessTransactionActionRepository;
    }

    public void setBusinessTransactionActionRepository(BusinessTransactionActionRepository businessTransactionActionRepository) {
        this.businessTransactionActionRepository = businessTransactionActionRepository;
    }
}

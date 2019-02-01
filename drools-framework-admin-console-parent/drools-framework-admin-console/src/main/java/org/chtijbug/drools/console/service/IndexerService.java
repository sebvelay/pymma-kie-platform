package org.chtijbug.drools.console.service;

import org.chtijbug.drools.indexer.persistence.repository.BusinessTransactionActionRepository;
import org.chtijbug.drools.indexer.persistence.repository.BusinessTransactionPersistenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.GregorianCalendar;

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
    public Date toDate(Integer years, Integer month, Integer days, Integer hours, Integer minutes, Integer millis ){

        return new GregorianCalendar(years,month-1,days,hours,minutes,millis).getTime();
    }

}

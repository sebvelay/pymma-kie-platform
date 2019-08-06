package org.chtijbug.drools.console.restExpose;

import org.chtijbug.drools.console.service.IndexerService;
import org.chtijbug.drools.indexer.persistence.model.BusinessTransactionAction;
import org.chtijbug.drools.indexer.persistence.model.BusinessTransactionPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/api/elasticSearch")
public class ElasticSearchExpose {

    @Autowired
    private IndexerService indexerService;

    @RequestMapping(value = "/findLastByTransactionId", method = RequestMethod.GET)
    public BusinessTransactionPersistence findTransactionById(@RequestParam String transactionId,HttpServletRequest request, HttpServletResponse response) {

        BusinessTransactionPersistence tmp=null;

        List<BusinessTransactionPersistence> businessTransactionPersistences = indexerService.getBusinessTransactionPersistenceRepository().findAllByTransactionId(transactionId,new PageRequest(0,5000));

        if(businessTransactionPersistences!=null) {
            for (BusinessTransactionPersistence b : businessTransactionPersistences) {

                if (tmp == null) {
                    tmp = b;
                }
                if (indexerService.toDate(tmp.getYear(), tmp.getMonth(), tmp.getDay(), tmp.getHour(), tmp.getMinute(), tmp.getMillis()).compareTo(
                        indexerService.toDate(b.getYear(), b.getMonth(), b.getDay(), b.getHour(), b.getMinute(), b.getMillis()))>0) {
                    tmp = b;
                }
            }
        }
        return tmp;
    }
    @RequestMapping(value = "/findActionByBusinessId", method = RequestMethod.GET)
    public List<BusinessTransactionAction> findActionById(@RequestParam String businessId, HttpServletRequest request, HttpServletResponse response) {

        List<BusinessTransactionAction> businessTransactionPersistences = indexerService.getBusinessTransactionActionRepository().findAllByBusinessTransactionId(businessId,new Sort(new Sort.Order(Sort.Direction.ASC,"eventNumber")),new PageRequest(0,5000));

        return businessTransactionPersistences;
    }

}

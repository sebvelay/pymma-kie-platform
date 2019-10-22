package org.chtijbug.drools.indexer.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.chtijbug.drools.SessionContext;
import org.chtijbug.drools.logging.*;
import org.chtijbug.drools.proxy.persistence.model.BusinessTransactionAction;
import org.chtijbug.drools.proxy.persistence.model.BusinessTransactionPersistence;
import org.chtijbug.drools.proxy.persistence.model.EventType;
import org.chtijbug.drools.proxy.persistence.repository.BusinessTransactionActionRepository;
import org.chtijbug.drools.proxy.persistence.repository.BusinessTransactionPersistenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

@Service("storeService")
public class StoreLoggingService {

    ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private BusinessTransactionPersistenceRepository transactionRepository;

    @Autowired
    private BusinessTransactionActionRepository actionRepository;

    public void store(String fileName, String fileContent) {
        if (fileName!= null) {

            String[] data = fileName.split("-");
            Integer year = Integer.valueOf(data[0]);
            Integer month = Integer.valueOf(data[1]);
            Integer day =  Integer.valueOf(data[2]);
            Integer hour =  Integer.valueOf(data[3]);
            Integer minute =  Integer.valueOf(data[4]);
            Integer second = Integer.valueOf(data[5]);
            Integer millis =  Integer.valueOf(data[6]);
            String id  = data[7].substring(0,data[7].indexOf("."));
            BusinessTransactionPersistence item = new BusinessTransactionPersistence();
            item.setYear(year);
            item.setMonth(month);
            item.setDay(day);
            item.setHour(hour);
            item.setMinute(minute);
            item.setSecond(second);
            item.setMillis(millis);
            item.setTransactionId(id);
            item.setId(UUID.randomUUID().toString());
            long ii=1;
            try {
                SessionContext sessionContext = mapper.readValue(fileContent,SessionContext.class);
                item.setContainerId(sessionContext.getContainerId());
                item.setGroupID(sessionContext.getGroupID());
                item.setArtefactID(sessionContext.getArtefactID());
                item.setVersion(sessionContext.getVersion());

                item.setServerName(sessionContext.getServerName());
                Map<Long, BusinessTransactionAction> actions = new HashMap<>();
                SessionExecution sessionExecution = sessionContext.getSessionExecution();
                BusinessTransactionAction businessTransactionoutput=null;
                for (Fact fact : sessionExecution.getFacts()){
                    BusinessTransactionAction businessTransactionAction = new BusinessTransactionAction();
                    businessTransactionAction.setId(UUID.randomUUID().toString());
                    businessTransactionAction.setBusinessTransactionId(item.getId());
                    if (fact.getFactType().equals(FactType.INPUTDATA)){
                        businessTransactionAction.setEventType(EventType.INPUT);
                        businessTransactionAction.setInputData(fact);
                        businessTransactionAction.setEventNumber(0);
                        actions.put(businessTransactionAction.getEventNumber(),businessTransactionAction);
                    }else  if (fact.getFactType().equals(FactType.OUTPUTDATA)){
                        businessTransactionAction.setEventType(EventType.OUPUT);
                        businessTransactionAction.setOutputData(fact);
                        businessTransactionoutput=businessTransactionAction;

                    }else if (fact.getFactType().equals(FactType.INSERTED)){
                        businessTransactionAction.setEventType(EventType.INSERTFACT);
                        businessTransactionAction.setFact(fact);
                        businessTransactionAction.setEventNumber(ii++);
                        actions.put(businessTransactionAction.getEventNumber(),businessTransactionAction);
                    } else if (fact.getFactType().equals(FactType.UPDATED_NEWVALUE)){
                        businessTransactionAction.setEventType(EventType.UPDATEFACTNEWVALUE);
                        businessTransactionAction.setFact(fact);
                        businessTransactionAction.setEventNumber(ii++);
                        actions.put(businessTransactionAction.getEventNumber(),businessTransactionAction);
                    }else if (fact.getFactType().equals(FactType.UPDATED_OLDVALUE)){
                        businessTransactionAction.setEventType(EventType.UPDATEFACTOLDVALUE);
                        businessTransactionAction.setFact(fact);
                        businessTransactionAction.setEventNumber(ii++);
                        actions.put(businessTransactionAction.getEventNumber(),businessTransactionAction);
                    }else  if (fact.getFactType().equals(FactType.DELETED)){
                        businessTransactionAction.setEventType(EventType.RETRACTFACT);
                        businessTransactionAction.setFact(fact);
                        businessTransactionAction.setEventNumber(ii++);
                        actions.put(businessTransactionAction.getEventNumber(),businessTransactionAction);
                    }
                }
                for (ProcessExecution processExecution : sessionExecution.getProcessExecutions()){
                    BusinessTransactionAction businessTransactionActionStart = new BusinessTransactionAction();

                    businessTransactionActionStart.setEventType(EventType.STARTPROCESS);
                    businessTransactionActionStart.setProcessID(processExecution.getProcessId());

                    businessTransactionActionStart.setBusinessTransactionId(item.getId());
                    businessTransactionActionStart.setEventNumber(ii++);
                    businessTransactionActionStart.setId(UUID.randomUUID().toString());
                    actions.put(businessTransactionActionStart.getEventNumber(),businessTransactionActionStart);
                    for (RuleflowGroup rfg : processExecution.getRuleflowGroups()){
                        BusinessTransactionAction businessTransactionActionStartRFG = new BusinessTransactionAction();

                        businessTransactionActionStartRFG.setBusinessTransactionId(item.getId());
                        businessTransactionActionStartRFG.setEventType(EventType.STARTRULEFLOWGROUP);
                        businessTransactionActionStartRFG.setRuleflowGroupName(rfg.getRuleflowGroup());
                        businessTransactionActionStartRFG.setEventNumber(ii++);
                        businessTransactionActionStartRFG.setId(UUID.randomUUID().toString());
                        actions.put(businessTransactionActionStartRFG.getEventNumber(),businessTransactionActionStartRFG);
                        for (RuleExecution ruleExecution : rfg.getRuleExecutionList()){
                            BusinessTransactionAction businessTransactionActionRule = new BusinessTransactionAction();

                            businessTransactionActionRule.setEventType(EventType.RULE);
                            businessTransactionActionRule.setRuleflowGroupName(rfg.getRuleflowGroup());
                            businessTransactionActionRule.setRuleExecution(ruleExecution);
                            businessTransactionActionRule.setBusinessTransactionId(item.getId());
                            businessTransactionActionRule.setProcessID(processExecution.getProcessId());
                            businessTransactionActionRule.setEventNumber(ii++);
                            businessTransactionActionRule.setId(UUID.randomUUID().toString());
                            actions.put(businessTransactionActionRule.getEventNumber(),businessTransactionActionRule);
                        }
                        BusinessTransactionAction businessTransactionActionSTOPRFG = new BusinessTransactionAction();

                        businessTransactionActionSTOPRFG.setEventType(EventType.STOPTRULEFLOWGROUP);
                        businessTransactionActionSTOPRFG.setRuleflowGroupName(rfg.getRuleflowGroup());
                        businessTransactionActionSTOPRFG.setBusinessTransactionId(item.getId());
                        businessTransactionActionSTOPRFG.setEventNumber(ii++);
                        businessTransactionActionSTOPRFG.setId(UUID.randomUUID().toString());
                        actions.put(businessTransactionActionSTOPRFG.getEventNumber(),businessTransactionActionSTOPRFG);
                    }
                    BusinessTransactionAction businessTransactionActionEnd = new BusinessTransactionAction();
                    businessTransactionActionEnd.setEventType(EventType.STOPPROCESS);
                    businessTransactionActionEnd.setProcessID(processExecution.getProcessId());
                    businessTransactionActionEnd.setBusinessTransactionId(item.getId());
                    businessTransactionActionEnd.setEventNumber(ii++);
                    businessTransactionActionEnd.setId(UUID.randomUUID().toString());

                    actions.put(businessTransactionActionEnd.getEventNumber(),businessTransactionActionEnd);
                }
                if (businessTransactionoutput!= null) {
                    businessTransactionoutput.setEventNumber(ii++);
                    actions.put(businessTransactionoutput.getEventNumber(), businessTransactionoutput);
                }
                List<Long> keys = new ArrayList<Long>(actions.keySet());
                Collections.sort(keys);
                List<BusinessTransactionAction> sortedList= new LinkedList<>();
                for (Long i : keys){
                    sortedList.add(actions.get(i));
                }
                transactionRepository.save(item);
                Iterable<BusinessTransactionAction> toto = actionRepository.saveAll(sortedList);
                System.out.println("");
            } catch (IOException e) {
                e.printStackTrace();
            }

            //item.setContent(fileContent);

        //    repository.save(item);
         }

        System.out.println("coucou");
    }
}

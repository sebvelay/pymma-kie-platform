package org.chtijbug.drools.proxy.persistence.model;


import org.chtijbug.drools.logging.Fact;
import org.chtijbug.drools.logging.RuleExecution;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;


@Document
public class BusinessTransactionAction {
    @Id
    private String id;

    @Field
    private EventType eventType;

    private long eventNumber;

    private String businessTransactionId;


    private Fact inputData;


    private Fact outputData;


    private RuleExecution ruleExecution;


    private Fact fact;


    private String processID;

    private String ruleflowGroupName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    public long getEventNumber() {
        return eventNumber;
    }

    public void setEventNumber(long eventNumber) {
        this.eventNumber = eventNumber;
    }

    public String getBusinessTransactionId() {
        return businessTransactionId;
    }

    public void setBusinessTransactionId(String businessTransactionId) {
        this.businessTransactionId = businessTransactionId;
    }

    public Fact getInputData() {
        return inputData;
    }

    public void setInputData(Fact inputData) {
        this.inputData = inputData;
    }

    public Fact getOutputData() {
        return outputData;
    }

    public void setOutputData(Fact outputData) {
        this.outputData = outputData;
    }

    public RuleExecution getRuleExecution() {
        return ruleExecution;
    }

    public void setRuleExecution(RuleExecution ruleExecution) {
        this.ruleExecution = ruleExecution;
    }

    public String getProcessID() {
        return processID;
    }

    public void setProcessID(String processID) {
        this.processID = processID;
    }

    public String getRuleflowGroupName() {
        return ruleflowGroupName;
    }

    public void setRuleflowGroupName(String ruleflowGroupName) {
        this.ruleflowGroupName = ruleflowGroupName;
    }

    public Fact getFact() {
        return fact;
    }

    public void setFact(Fact fact) {
        this.fact = fact;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("BusinessTransactionAction{");
        sb.append("id='").append(id).append('\'');
        sb.append(", eventType=").append(eventType);
        sb.append(", eventNumber=").append(eventNumber);
        sb.append(", businessTransactionId='").append(businessTransactionId).append('\'');
        sb.append(", inputData=").append(inputData);
        sb.append(", outputData=").append(outputData);
        sb.append(", ruleExecution=").append(ruleExecution);
        sb.append(", fact=").append(fact);
        sb.append(", processID='").append(processID).append('\'');
        sb.append(", ruleflowGroupName='").append(ruleflowGroupName).append('\'');
        sb.append('}');
        return sb.toString();
    }
}

package org.chtijbug.drools.proxy.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.chtijbug.drools.kieserver.extension.KieServerAddOnElement;
import org.chtijbug.drools.kieserver.extension.KieServerLoggingDefinition;
import org.chtijbug.drools.logging.SessionExecution;
import org.chtijbug.kieserver.services.drools.ChtijbugObjectRequest;
import org.kie.server.services.api.KieContainerInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoField;
import java.util.Set;

@Service("ruleService")
public class RuleService {

    private static final Logger logger = LoggerFactory.getLogger(RuleService.class);
    private ObjectMapper mapper = new ObjectMapper();
    @Inject
    private KieServiceCommon kieServiceCommon;

    public RuleService() {
        System.out.println("rulestep01");
    }

    public Object runSessionObject(String transactionID, String id, String processID, Object input) throws IOException {
        KieContainerInstance kci = kieServiceCommon.getRegistry().getContainer(id);
        ChtijbugObjectRequest chtijbugObjectRequest = new ChtijbugObjectRequest();
        chtijbugObjectRequest.setObjectRequest(input);
        KieServerAddOnElement kieServerAddOnElement = kieServiceCommon.getDroolsChtijbugRulesExecutionService().getKieServerAddOnElement();
        if (kieServerAddOnElement != null) {
            for (KieServerLoggingDefinition kieServerLoggingDefinition : kieServerAddOnElement.getKieServerLoggingDefinitions()) {
                kieServerLoggingDefinition.OnFireAllrulesStart(kci.getKieContainer().getReleaseId().getGroupId(), kci.getKieContainer().getReleaseId().getArtifactId(), kci.getKieContainer().getReleaseId().getVersion(), input);
            }
        }
        ChtijbugObjectRequest chtijbutObjectResponse = kieServiceCommon.getDroolsChtijbugRulesExecutionService().FireAllRulesAndStartProcess(kci, chtijbugObjectRequest, processID);
        /**
         * remove facts from logging to avoid infinite loop when marshalling to json and size of logging
         */
        SessionExecution sessionExecution = chtijbutObjectResponse.getSessionLogging().getSessionExecution();
        if (kieServerAddOnElement != null) {

            for (KieServerLoggingDefinition kieServerLoggingDefinition : kieServerAddOnElement.getKieServerLoggingDefinitions()) {
                kieServerLoggingDefinition.OnFireAllrulesEnd(kci.getKieContainer().getReleaseId().getGroupId(), kci.getKieContainer().getReleaseId().getArtifactId(), kci.getKieContainer().getReleaseId().getVersion(), chtijbutObjectResponse.getObjectRequest(), chtijbutObjectResponse.getSessionLogging());
            }
        }
        String jsonInString = null;

        String fileTemp = System.getProperty("org.chtijbug.server.tracedir");
        if (fileTemp != null) {
            if (jsonInString == null) {
                jsonInString = mapper.writeValueAsString(chtijbutObjectResponse.getSessionLogging());
            }
            String fileUUID = null;
            if (transactionID == null) {
                fileUUID = "noTransactionID";
            } else {
                fileUUID = transactionID;
            }
            LocalDateTime now = LocalDateTime.now();
            int year = now.getYear();
            int month = now.getMonthValue();
            int day = now.getDayOfMonth();
            int hour = now.getHour();
            int minute = now.getMinute();
            int second = now.getSecond();
            int millis = now.get(ChronoField.MILLI_OF_SECOND);
            String fileName = year + "-" + month + "-" + day + "-" + hour + "-" + minute + "-" + second + "-" + millis + "-" + fileUUID.replaceAll("-", "") + ".json";
            File traceFile = new File(fileTemp + "/" + fileName);
            FileUtils.writeByteArrayToFile(traceFile, jsonInString.getBytes());
        }

        Object response = chtijbutObjectResponse.getObjectRequest();
        return response;
    }


    private Class getClassFromName(Set<Class<?>> classes, String name) {
        Class result = null;
        for (Class c : classes) {
            if (c.getCanonicalName() != null
                    && c.getCanonicalName().equals(name)) {
                result = c;
                break;
            }
        }
        return result;
    }
}

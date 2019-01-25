package org.chtijbug.drools.proxy.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.chtijbug.drools.kieserver.extension.KieServerAddOnElement;
import org.chtijbug.drools.kieserver.extension.KieServerLoggingDefinition;
import org.chtijbug.drools.logging.SessionExecution;
import org.chtijbug.kieserver.services.drools.ChtijbugObjectRequest;
import org.chtijbug.kieserver.services.drools.DroolsChtijbugKieServerExtension;
import org.chtijbug.kieserver.services.drools.DroolsChtijbugRulesExecutionService;
import org.kie.server.services.api.KieContainerInstance;
import org.kie.server.services.api.KieServerExtension;
import org.kie.server.services.api.KieServerRegistry;
import org.kie.server.services.impl.KieServerImpl;
import org.kie.server.services.impl.KieServerLocator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service("ruleService")
public class RuleService {

    private DroolsChtijbugRulesExecutionService droolsChtijbugRulesExecutionService = null;

    private DroolsChtijbugKieServerExtension droolsChtijbugKieServerExtension;

    private KieServerRegistry registry;
    private ObjectMapper mapper = new ObjectMapper();

    private KieServerImpl server;

    private static final Logger logger = LoggerFactory.getLogger(RuleService.class);



    public RuleService() {
        this.server = KieServerLocator.getInstance();
        List<KieServerExtension> serverExtensions = this.server.getServerExtensions();
        for (KieServerExtension serverExtension : serverExtensions) {
            if (serverExtension instanceof DroolsChtijbugKieServerExtension) {
                droolsChtijbugKieServerExtension = (DroolsChtijbugKieServerExtension) serverExtension;
                if (droolsChtijbugRulesExecutionService == null) {

                    droolsChtijbugRulesExecutionService = droolsChtijbugKieServerExtension.getRulesExecutionService();
                }
                if (registry == null) {
                    registry = droolsChtijbugRulesExecutionService.getContext();
                }
            }
        }
    }

    public Object runSessionObject(String id, String processID, Object input) throws IOException {
        KieContainerInstance kci = registry.getContainer(id);
        ChtijbugObjectRequest chtijbugObjectRequest = new ChtijbugObjectRequest();
        chtijbugObjectRequest.setObjectRequest(input);
        KieServerAddOnElement kieServerAddOnElement = droolsChtijbugRulesExecutionService.getKieServerAddOnElement();
        if (kieServerAddOnElement != null) {
            for (KieServerLoggingDefinition kieServerLoggingDefinition : kieServerAddOnElement.getKieServerLoggingDefinitions()) {
                kieServerLoggingDefinition.OnFireAllrulesStart(kci.getKieContainer().getReleaseId().getGroupId(), kci.getKieContainer().getReleaseId().getArtifactId(), kci.getKieContainer().getReleaseId().getVersion(), input);
            }
        }
        ChtijbugObjectRequest chtijbutObjectResponse = droolsChtijbugRulesExecutionService.FireAllRulesAndStartProcess(kci, chtijbugObjectRequest, processID);
        /**
         * remove facts from logging to avoid infinite loop when marshalling to json and size of logging
         */
        SessionExecution sessionExecution = chtijbutObjectResponse.getSessionLogging().getSessionExecution();
        sessionExecution.getFacts().clear();
        if (kieServerAddOnElement != null) {

            for (KieServerLoggingDefinition kieServerLoggingDefinition : kieServerAddOnElement.getKieServerLoggingDefinitions()) {
                kieServerLoggingDefinition.OnFireAllrulesEnd(kci.getKieContainer().getReleaseId().getGroupId(), kci.getKieContainer().getReleaseId().getArtifactId(), kci.getKieContainer().getReleaseId().getVersion(), chtijbutObjectResponse.getObjectRequest(), chtijbutObjectResponse.getSessionLogging());
            }
        }
        String jsonInString = null;
        String filetrace = System.getProperty("org.chtijbug.server.tracelog");
        if ("true".equals(filetrace)) {
            try {
                Class foundClass = input.getClass();
                Method traceMethod = foundClass.getMethod("logTrace", String.class);
                if (traceMethod != null) {
                    jsonInString = mapper.writeValueAsString(chtijbutObjectResponse.getSessionLogging());
                }
                traceMethod.invoke(input, jsonInString);
            } catch (Exception e) {

            }
        }
        String fileTemp = System.getProperty("org.chtijbug.server.tracedir");
        if (fileTemp != null) {
            if (jsonInString == null) {
                jsonInString = mapper.writeValueAsString(chtijbutObjectResponse.getSessionLogging());
            }
            File traceFile = new File(fileTemp + "/" + UUID.randomUUID().toString());
            FileUtils.writeStringToFile(traceFile, jsonInString);
        }

        Object response = chtijbutObjectResponse.getObjectRequest();
        return response;
    }

    public String runSession(String id, String processID, String className,
                             String objectRequest) {


        ClassLoader localClassLoader = null;
        Object response = null;
        try {
            localClassLoader = Thread.currentThread()
                    .getContextClassLoader();
        } catch (ClassCastException e) {
            logger.info("GenericResource.runSession", e);
        }
        ChtijbugObjectRequest chtijbugObjectRequest = new ChtijbugObjectRequest();
        try {

            KieContainerInstance kci = registry.getContainer(id);

            Set<Class<?>> classes = kci.getExtraClasses();
            Class foundClass = this.getClassFromName(classes, className);
            if (foundClass != null) {
                ClassLoader classLoader = foundClass.getClassLoader();
                Object input = mapper.readValue(objectRequest, classLoader.loadClass(className));
                response = this.runSessionObject(id, processID, input);
            }
            //response.setSessionLogging(jsonInString);
            logger.debug("Returning OK response with content '{}'", response);
            String responseText = mapper.writeValueAsString(response);
            return responseText;
        } catch (Exception e) {
            // in case marshalling failed return the FireAllRulesAndStartProcess container response to keep backward compatibility
            String responseMessage = "Execution failed with error : " + e.getMessage();
            logger.debug("Returning Failure response with content '{}'", responseMessage);
            return objectRequest;
        } finally {
            if (localClassLoader != null) {
                Thread.currentThread().setContextClassLoader(localClassLoader);
            }
        }

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

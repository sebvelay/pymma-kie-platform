package org.chtijbug.drools.proxy.camel;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.kie.server.services.api.KieContainerInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.apache.camel.model.rest.RestParamType.body;
import static org.apache.camel.model.rest.RestParamType.path;

public class DroolsRouter extends RouteBuilder {

    private static final Logger logger = LoggerFactory.getLogger(DroolsRouter.class);
    private String projectName ;
    private KieContainerInstance kci;
    private Class<?> clazzUser;

    public DroolsRouter(CamelContext camelContext,Class<?> clazzUser, String projectName,KieContainerInstance kci) {
        super(camelContext);
        this.clazzUser = clazzUser;
        this.projectName=projectName;
        this.kci = kci;
    }

    @Override
    public void configure() throws Exception {

            System.out.println("coucou");

            rest("/" + projectName).description(projectName + " Rest service")
                    .consumes("application/json")
                    .produces("application/json")

                    .put("/{containerId}/{processID}").description("Execute Business Service").type(clazzUser).outType(clazzUser)
                    .param().name("containerId").type(path).description("Container  ID where the rule artefact id deployed").dataType("integer").endParam()
                    .param().name("processID").type(path).description("process ID sot start").dataType("integer").endParam()
                    .param().name("body").type(body).description("The Data drools should work on").endParam()
                    .responseMessage().code(200).message("Data drools worked on").endResponseMessage()
                    .to("bean:ruleService?method=runSessionObject(${header.containerId},${header.processID},${body})");


    }
}

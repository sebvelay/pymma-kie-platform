package org.chtijbug.drools.proxy.camel;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.kie.server.services.api.KieContainerInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.apache.camel.model.rest.RestParamType.body;

public class DroolsRouter extends RouteBuilder {

    private static final Logger logger = LoggerFactory.getLogger(DroolsRouter.class);
    private String containerId;
    private KieContainerInstance kci;
    private Class<?> clazzUser;
    private String processID;

    public DroolsRouter(CamelContext camelContext, Class<?> clazzUser, String containerId, KieContainerInstance kci, String processID) {
        super(camelContext);
        this.clazzUser = clazzUser;
        this.containerId = containerId;
        this.kci = kci;
        this.processID = processID;
    }

    @Override
    public void configure() throws Exception {

            System.out.println("coucou");

            rest("/" + containerId).description(containerId + " Rest service")
                    .consumes("application/json")
                    .produces("application/json")

                    .put("/").description("Execute Business Service").type(clazzUser).outType(clazzUser)
                  //  .param().name("containerId").type(path).description("Container  ID where the rule artefact id deployed").dataType("integer").endParam()
                    .param().name("body").type(body).description("The Data drools should work on").endParam()
                    .responseMessage().code(200).message("Data drools worked on").endResponseMessage()
                    .to("bean:ruleService?method=runSessionObject(${header.transactionId},"+this.containerId+","+this.processID+",${body})");


    }
}

package org.chtijbug.drools.proxy.camel;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.chtijbug.drools.proxy.service.RuleService;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.Resource;

import static org.apache.camel.model.rest.RestParamType.body;
import static org.apache.camel.model.rest.RestParamType.path;

//@Component
public class GenericDroolsRouter extends RouteBuilder {
    @Value("${server.port}")
    private int serverPort;
    @Value("${camel.component.servlet.mapping.context-path}")
    private String contextPath;

    @Resource
    RuleService ruleServicee;

    @Override
    public void configure() throws Exception {

        // @formatter:off

        // this can also be configured in application.properties
        restConfiguration()
                .component("servlet")
                .bindingMode(RestBindingMode.json)
                .dataFormatProperty("prettyPrint", "true")
                .enableCORS(true)
                .port(serverPort)
                .contextPath(contextPath.substring(0, contextPath.length() - 2))
                // turn on swagger api-doc
                .apiContextPath("/api-doc")
                .apiProperty("api.title", "Generic drools API")
                .apiProperty("api.version", "1.0.0");

        rest("containers/generic/run").description("Drools Generic Rest service")
                .consumes("application/json")
                .produces("application/json")

                .post("/{id}/{processId}/{className}").description("Find user by ID")
                .param().name("id").type(path).description("Container  ID where the rule are located").dataType("integer").endParam()
                .param().name("processId").type(path).description("process ID sot start").dataType("integer").endParam()
                .param().name("className").type(path).description("Class name of the top class").dataType("integer").endParam()
                .param().name("body").type(body).description("The user data").endParam()
                .responseMessage().code(200).message("User successfully returned").endResponseMessage()
                .to("bean:ruleService?method=runSession(${header.id},${header.processId},${header.className},${body})");


    }
}

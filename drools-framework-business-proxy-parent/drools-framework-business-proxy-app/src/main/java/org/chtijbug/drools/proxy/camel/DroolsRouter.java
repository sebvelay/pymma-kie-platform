package org.chtijbug.drools.proxy.camel;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import static org.apache.camel.model.rest.RestParamType.body;
import static org.apache.camel.model.rest.RestParamType.path;

@Component
public class DroolsRouter extends RouteBuilder {
    private int serverPort = 8080;
    @Value("${camel.component.servlet.mapping.context-path}")
    private String contextPath;

    private String projectName = "proBTP";

    private String containerId = "1";

    private String processID = "P0";

    private Class<?> clazzUser;

    {
        try {
            clazzUser = Class.forName("org.chtijbug.drools.proxy.camel.User");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


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
                .apiProperty("api.title", projectName + " API")
                .apiProperty("api.version", "1.0.0");

        rest("/" + projectName).description(projectName + " Rest service")
                .consumes("application/json")
                .produces("application/json")

                .put("/{containerId}/{processID}").description("Find user by ID").type(clazzUser).outType(clazzUser)
                .param().name("containerId").type(path).description("Container  ID where the rule artefact id deployed").dataType("integer").endParam()
                .param().name("processID").type(path).description("process ID sot start").dataType("integer").endParam()
                .param().name("body").type(body).description("The user to update").endParam()
                .responseMessage().code(200).message("User successfully returned").endResponseMessage()
                .to("bean:userService?method=calculateUser(${header.containerId},${header.processID},${body})");


    }
}

package org.chtijbug.drools.proxy.camel;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.chtijbug.drools.proxy.service.KieServiceCommon;
import org.kie.server.api.model.KieServerInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class AdminRouter extends RouteBuilder {


    @Resource
    KieServiceCommon kieServiceCommon;
    @Value("${server.port}")
    private int serverPort;
    @Value("${camel.component.servlet.mapping.context-path}")
    private String contextPath;

    @Override
    public void configure() throws Exception {
        System.out.println("coucou");

        restConfiguration()
                .component("servlet")
                .bindingMode(RestBindingMode.json)
                .dataFormatProperty("prettyPrint", "true")
                .enableCORS(true)
                .port(serverPort)
                .contextPath(contextPath.substring(0, contextPath.length() - 2))
                // turn on swagger api-doc
                .apiContextPath("/api-doc")
                .apiProperty("api.title", "KIE Server :: Core")
                .apiProperty("api.version", "1.0.0");

        rest("/server/details").description("Retrieves containers deployed to this server, optionally filtered by group, artifact, version or status")
                .produces("application/json")
                .consumes("application/json")
                .get("/").description("Retrieves KIE Server information - id, name, location, capabilities, messages").outType(KieServerInfo.class)
                .responseMessage().code(200).message("Server Info successfully returned").endResponseMessage()
                .to("bean:kieService?method=getInfo()");
    }
}

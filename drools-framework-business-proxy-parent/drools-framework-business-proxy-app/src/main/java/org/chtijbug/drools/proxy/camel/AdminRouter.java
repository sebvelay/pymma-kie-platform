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


    @Value("${server.port}")
    private int serverPort;
    @Value("${camel.component.servlet.mapping.context-path}")
    private String contextPath;
    @Resource
    KieServiceCommon kieServiceCommon;
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
                .apiProperty("api.title","KIE Server :: Core")
                .apiProperty("api.version", "1.0.0");

        rest("/server/details").description("Retrieves containers deployed to this server, optionally filtered by group, artifact, version or status")
                .produces("application/json")
                .consumes("application/json")
                .get("/").description("Retrieves KIE Server information - id, name, location, capabilities, messages").outType(KieServerInfo.class)
                .responseMessage().code(200).message("Server Info successfully returned").endResponseMessage()
                .to("bean:kieService?method=getInfo()");
/**
        rest("/server/containers").description("Retrieves containers deployed to this server, optionally filtered by group, artifact, version or status")
                .produces("application/json")
                .consumes("application/json")
                .get("/").description("Find user by ID").outType(KieContainerResourceList.class)
                .param().name("groupId").type(RestParamType.query).required(false).description("optional groupId to filter containers by").dataType("string").endParam()
                .param().name("artifactId").type(RestParamType.query).required(false).description("optional artifactId to filter containers by").dataType("string").endParam()
                .param().name("version").type(RestParamType.query).required(false).description("optional version to filter containers by").dataType("string").endParam()
                .param().name("status").type(RestParamType.query).required(false).description("optional status to filter containers by").dataType("string").endParam()

                .responseMessage().code(200).message("containers successfully returned").endResponseMessage()
                .to("bean:kieService?method=listContainers(${header.groupId},${header.artifactId},${header.version},${header.status})");

        rest("/server/containers").description(" Creates (deploys) new KIE container to this server")
                .consumes("application/json")
                .produces("application/json")

                .put("/{id}").description("Container id to be assigned to deployed KIE Container").type(KieContainerResource.class).outType(KieContainerResource.class)
                .param().name("id").type(path).description("Container id to be assigned to deployed KIE Container").dataType("string").endParam()
                .param().name("body").type(body).description("KIE Container resource to be deployed as KieContainerResourcee").endParam()
                .responseMessage().code(200).message("Container successfully created").endResponseMessage()
                .to("bean:kieService?method=createContainer(${header.id},${body})");

        rest("/server/containers").description(" Disposes (undeploys) container with given id")
                .consumes("application/json")
                .produces("application/json")

                .delete("/{id}").description("Container id to be disposed (undeployed)r")
                .param().name("id").type(path).description("Container id to be disposed (undeployed)").dataType("string").endParam()
                 .responseMessage().code(200).message("Container successfully created").endResponseMessage()
                .to("bean:kieService?method=disposeContainer(${header.id})");

        rest("/server/containers/business").description(" Creates (deploys) new KIE container to this server with a business rest interface")
                .consumes("application/json")
                .produces("application/json")

                .post("/{id}/{className}/{processID}").description("Container id to be assigned to deployed KIE Container").type(KieContainerResource.class).outType(KieContainerResource.class)
                .param().name("id").type(path).description("Container id to be assigned to deployed KIE Container").dataType("string").endParam()
                .param().name("className").type(path).description("Class Name of the Business Service").dataType("string").endParam()
                .param().name("processID").type(path).description("Process Id of the Business Service").dataType("string").endParam()
                 .param().name("body").type(body).description("KIE Container resource to be deployed as KieContainerResourcee").endParam()
                .responseMessage().code(200).message("Container successfully created").endResponseMessage()
                .to("bean:kieService?method=createContainerWithRestBusinessService(${header.id},${body},${header.className},${header.processID})");
    **/
 }
}

package org.chtijbug.drools.proxy.camel;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.apache.camel.model.rest.RestParamType;
import org.chtijbug.drools.proxy.persistence.model.RuntimePersist;
import org.chtijbug.drools.proxy.service.KieServiceCommon;
import org.kie.server.api.model.KieContainerResource;
import org.kie.server.api.model.KieContainerResourceList;
import org.kie.server.api.model.KieServerInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

import static org.apache.camel.model.rest.RestParamType.body;
import static org.apache.camel.model.rest.RestParamType.path;

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
    }
}
/**





    @ApiOperation(value="Retrieves server state - configuration that the server is currently running with",
            response=ServiceResponse.class, code=200)
    @ApiResponses(value = { @ApiResponse(code = 500, message = "Unexpected error") })
    @GET
    @Path("state")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response getServerState(@Context HttpHeaders headers) {
        return createCorrectVariant(server.getServerState(), headers);
    }



    @ApiOperation(value="Readiness check for KIE Server that indicates that server is fully booted and ready to accept requests",
            response=Void.class, code=200)
    @ApiResponses(value = { @ApiResponse(code = 503, message = "Service not yet available") })
    @GET
    @Path("readycheck")
    @Produces({MediaType.TEXT_PLAIN})
    public Response readycheck(@Context HttpHeaders headers) {
        if (server.isKieServerReady()) {
            return Response.status(Response.Status.OK).build();
        }
        return serviceUnavailable();
    }

    @ApiOperation(value="Liveness check for KIE Server that validates both kie server and all extensions, optionally produces report",
            response=Message.class, code=200, responseContainer="List")
    @ApiResponses(value = { @ApiResponse(code = 503, message = "If any of the checks failed") })
    @GET
    @Path("healthcheck")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response healthcheck(@Context HttpHeaders headers,
                                @ApiParam(value = "optional report flag to return detailed report of the check, defaults to false", required = false) @QueryParam("report")  @DefaultValue("false") boolean report) {
        List<Message> healthMessages = server.healthCheck(report);

        boolean anyfailures = healthMessages.stream().anyMatch(msg -> msg.getSeverity().equals(Severity.ERROR));
        if (anyfailures) {
            if (report) {
                return createCorrectVariant(healthMessages, headers, Response.Status.SERVICE_UNAVAILABLE);
            }

            return serviceUnavailable();
        }
        if (report) {
            return createCorrectVariant(healthMessages, headers, Response.Status.OK);
        }
        return Response.status(Response.Status.OK).build();
    }

}

 **/
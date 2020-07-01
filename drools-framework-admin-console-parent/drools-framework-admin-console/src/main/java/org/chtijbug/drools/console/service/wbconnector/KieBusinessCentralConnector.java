package org.chtijbug.drools.console.service.wbconnector;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationModule;
import org.apache.commons.codec.binary.Base64;
import org.chtijbug.drools.proxy.persistence.model.ProjectPersist;
import org.kie.server.api.model.*;
import org.kie.server.controller.api.model.KieServerSetup;
import org.kie.server.controller.api.model.runtime.ServerInstanceKeyList;
import org.kie.server.controller.api.model.spec.ContainerSpec;
import org.kie.server.controller.api.model.spec.ServerTemplateKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

@Service
public class KieBusinessCentralConnector {

    @Value(value = "${org.kie.server.id}")
    private String kieserverID;

    @Value("${org.kie.server.controller}")
    private String kiewbUrl;

    @Value("${org.kie.server.location}")
    private String controlerLocation;

    private RestTemplate restTemplateKiewb ;
    private ObjectMapper mapper = new ObjectMapper();

    public KieBusinessCentralConnector() {
        ClientHttpRequestFactory factory = new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory());
        restTemplateKiewb= new RestTemplate(factory);
        restTemplateKiewb.setInterceptors(Collections.singletonList(new LoggingRequestResponseLoggingInterceptor()));
        mapper.registerModule(new JaxbAnnotationModule());
    }

    public String createContainer(String username, String password, ProjectPersist projectPersist){
        String completeurl=kiewbUrl+"/controller/management/servers/"+kieserverID+"/containers/"+projectPersist.getContainerID();
        ContainerSpec containerSpec = new ContainerSpec();
        containerSpec.setId(projectPersist.getContainerID());
        containerSpec.setContainerName(projectPersist.getContainerID());
        containerSpec.setReleasedId(new ReleaseId());
        containerSpec.setStatus(KieContainerStatus.STARTED);
        containerSpec.getReleasedId().setArtifactId(projectPersist.getArtifactID());
        containerSpec.getReleasedId().setGroupId(projectPersist.getGroupID());
        containerSpec.getReleasedId().setVersion(projectPersist.getProjectVersion());

        ResponseEntity<String> response = restTemplateKiewb
                .execute(completeurl, HttpMethod.PUT, requestCallback(containerSpec, username, password), clientHttpResponse -> {
                    String extractedResponse = null;
                    ResponseEntity<String> extractedValue=null;
                    if (clientHttpResponse.getBody() != null) {
                        Scanner s = new Scanner(clientHttpResponse.getBody()).useDelimiter("\\A");
                        String result = s.hasNext() ? s.next() : "";
                        if (result==null || result.length()==0){

                        }else {
                            extractedResponse = mapper.readValue(result, String.class);
                            extractedValue = new ResponseEntity<>(extractedResponse, clientHttpResponse.getHeaders(), clientHttpResponse.getStatusCode());
                        }
                    }

                    return extractedValue;
                });
        if (response!= null) {
            return response.getBody();
        }else {
            return null;
        }
    }

    public String updateContainer(String username, String password, ProjectPersist projectPersist, KieContainerResource kieContainerResource){
        String completeurl=kiewbUrl+"/controller/management/servers/"+kieserverID+"/containers/"+projectPersist.getContainerID();
        ContainerSpec containerSpec = new ContainerSpec();
        containerSpec.setId(projectPersist.getContainerID());
        containerSpec.setContainerName(projectPersist.getContainerID());
        containerSpec.setReleasedId(new ReleaseId());
        containerSpec.setStatus(KieContainerStatus.STARTED);
        containerSpec.getReleasedId().setArtifactId(projectPersist.getArtifactID());
        containerSpec.getReleasedId().setGroupId(projectPersist.getGroupID());
        containerSpec.getReleasedId().setVersion(projectPersist.getProjectVersion());
        containerSpec.setServerTemplateKey(new ServerTemplateKey());
        containerSpec.getServerTemplateKey().setId(kieserverID);
        containerSpec.getServerTemplateKey().setName(kieserverID);
        ResponseEntity<String> response = restTemplateKiewb
                .execute(completeurl, HttpMethod.POST, requestCallback(containerSpec, username, password), clientHttpResponse -> {
                    String extractedResponse = null;
                    ResponseEntity<String> extractedValue=null;
                    if (clientHttpResponse.getBody() != null) {
                        Scanner s = new Scanner(clientHttpResponse.getBody()).useDelimiter("\\A");
                        String result = s.hasNext() ? s.next() : "";
                        if (result==null || result.length()==0){

                        }else {
                            extractedResponse = mapper.readValue(result, String.class);
                            extractedValue = new ResponseEntity<>(extractedResponse, clientHttpResponse.getHeaders(), clientHttpResponse.getStatusCode());
                        }
                    }

                    return extractedValue;
                });
        if (response!= null) {
            return response.getBody();
        }else {
            return null;
        }
    }

    public ServerInstanceKeyList getListInstances(String username, String password){
        String completeurl=kiewbUrl+"/controller/runtime/servers/"+kieserverID+"/instances";

        ResponseEntity<ServerInstanceKeyList> response = restTemplateKiewb
                .execute(completeurl, HttpMethod.GET, requestCallback(null, username, password), clientHttpResponse -> {
                    ServerInstanceKeyList extractedResponse;
                    ResponseEntity<ServerInstanceKeyList> extractedValue=null;

                    if (clientHttpResponse.getBody() != null) {
                        //Scanner s = new Scanner(clientHttpResponse.getBody()).useDelimiter("\\A");
                        //String result = s.hasNext() ? s.next() : "";
                        String result = StreamUtils.copyToString(clientHttpResponse.getBody(), Charset.defaultCharset());
                        if (result==null || result.length()==0){

                        }else {
                            extractedResponse = mapper.readValue(result, ServerInstanceKeyList.class);
                            extractedValue = new ResponseEntity<>(extractedResponse, clientHttpResponse.getHeaders(), clientHttpResponse.getStatusCode());
                        }
                    }

                    return extractedValue;
                });
        if (response!= null) {
            return response.getBody();
        }else {
            return null;
        }
    }

    public KieServerSetup connectToBusinessCentral(String username, String password){
        String completeurl=kiewbUrl+"/controller/server/"+kieserverID;
        KieServerInfo kieServerInfo = new KieServerInfo();
        kieServerInfo.setVersion("1.0.0");
        kieServerInfo.setServerId("1");
        kieServerInfo.setName(kieserverID);
        kieServerInfo.setMode(KieServerMode.DEVELOPMENT);
        kieServerInfo.setServerId(kieserverID);
        kieServerInfo.setLocation(controlerLocation);
        kieServerInfo.setCapabilities(new ArrayList<>());
        kieServerInfo.getCapabilities().add("BRM");
        ResponseEntity<KieServerSetup> response = restTemplateKiewb
                .execute(completeurl, HttpMethod.PUT, requestCallback(kieServerInfo, username, password), clientHttpResponse -> {
                    KieServerSetup extractedResponse = null;
                    ResponseEntity<KieServerSetup> extractedValue=null;
                    if (clientHttpResponse.getBody() != null) {
                        Scanner s = new Scanner(clientHttpResponse.getBody()).useDelimiter("\\A");
                        String result = s.hasNext() ? s.next() : "";
                        if (result==null || result.length()==0){

                        }else {
                            extractedResponse = mapper.readValue(result, KieServerSetup.class);
                            extractedValue = new ResponseEntity<>(extractedResponse, clientHttpResponse.getHeaders(), clientHttpResponse.getStatusCode());
                        }
                    }

                    return extractedValue;
                });
        if (response!= null) {
            return response.getBody();
        }else {
            return null;
        }
    }
    private RequestCallback requestCallback(final Object content, String username, String password) {
        return clientHttpRequest -> {

            if (content != null) {
                if (content instanceof KieServerInfo) {
                    KieServerInfo kieServerSetup = (KieServerInfo) content;

                    mapper.writeValue(clientHttpRequest.getBody(), kieServerSetup);
                } else {
                    mapper.writeValue(clientHttpRequest.getBody(), content);
                }
            }
            clientHttpRequest.getHeaders().add(
                    HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
            clientHttpRequest.getHeaders().add(
                    HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
            String auth = username + ":" + password;
            byte[] encodedAuth = Base64.encodeBase64(
                    auth.getBytes(Charset.forName("UTF-8")));
            String authHeader = "Basic " + new String(encodedAuth);
            clientHttpRequest.getHeaders().add(
                    HttpHeaders.AUTHORIZATION, authHeader);
        };
    }
}

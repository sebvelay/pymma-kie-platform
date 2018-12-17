package org.chtijbug.drools.console.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.codec.binary.Base64;
import org.chtijbug.drools.console.AddLog;
import org.chtijbug.drools.console.service.model.kie.KieContainerInfo;
import org.chtijbug.drools.console.service.model.kie.KieContainerRequest;
import org.chtijbug.drools.console.service.model.kie.KieServerJobStatus;
import org.chtijbug.drools.console.service.model.kie.SpaceProject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

@Service
public class KieServerRepositoryService {


    private static Logger logger = LoggerFactory.getLogger(KieServerRepositoryService.class);


    private RestTemplate restTemplateKiewb = new RestTemplate();

    private ObjectMapper mapper = new ObjectMapper();

    ///spaces/{spaceName}/projects/{projectName}

    public void getProjectContent(String url, String username, String password, String space, String project) {
        String completeurl = url + "/spaces/" + space + "/projects/" + project;
        logger.info("url project content : " + completeurl);
        ResponseEntity<SpaceProject> response = restTemplateKiewb
                .execute(completeurl, HttpMethod.GET, requestCallback(null, username, password), clientHttpResponse -> {
                    SpaceProject extractedResponse = null;
                    if (clientHttpResponse.getBody() != null) {
                        Scanner s = new Scanner(clientHttpResponse.getBody()).useDelimiter("\\A");
                        String result = s.hasNext() ? s.next() : "";

                        SpaceProject values = mapper.readValue(result, SpaceProject.class);
                        extractedResponse = values;
                    }
                    ResponseEntity<SpaceProject> extractedValue = new ResponseEntity<>(extractedResponse, clientHttpResponse.getHeaders(), clientHttpResponse.getStatusCode());
                    return extractedValue;
                });
        SpaceProject reponseMoteur;

        reponseMoteur = response.getBody();
        System.out.println(reponseMoteur);
    }


    public List<KieContainerInfo> getContainerList(String url, String username, String password) {
        List<KieContainerInfo> results = new ArrayList<>();
        String completeurl = url + "/containers";
        logger.info("url kie server container : " + completeurl);
        ResponseEntity<Map<String, Object>> response = restTemplateKiewb
                .execute(completeurl, HttpMethod.GET, requestCallback(null, username, password), clientHttpResponse -> {
                    Map<String, Object> extractedResponse = null;
                    if (clientHttpResponse.getBody() != null) {
                        Scanner s = new Scanner(clientHttpResponse.getBody()).useDelimiter("\\A");
                        String result = s.hasNext() ? s.next() : "";
                        Map<String, Object> map = mapper.readValue(
                                result,
                                new TypeReference<Map<String, Object>>() {
                                });
                        Map<String, Object> values = mapper.readValue(result, Map.class);
                        extractedResponse = values;
                    }
                    ResponseEntity<Map<String, Object>> extractedValue = new ResponseEntity<>(extractedResponse, clientHttpResponse.getHeaders(), clientHttpResponse.getStatusCode());
                    return extractedValue;
                });
        Map<String, Object> reponseMoteur;

        reponseMoteur = response.getBody();
        Object resultMap = reponseMoteur.get("result");
        Object containersMap = ((Map) resultMap).get("kie-containers");
        Map containerMap = ((Map) containersMap);
        List<Map<String, Object>> listContainers = (List) containerMap.get("kie-container");
        for (Map<String, Object> container : listContainers) {
            KieContainerInfo kieContainerInfo = new KieContainerInfo();
            System.out.println("coucou");
            kieContainerInfo.setContainerId((String) container.get("container-id"));
            Map artifact = (Map) container.get("resolved-release-id");
            if (artifact != null) {
                kieContainerInfo.setArtifactId((String) artifact.get("artifact-id"));
                kieContainerInfo.setGroupId((String) artifact.get("group-id"));
                kieContainerInfo.setVersion((String) artifact.get("version"));
            }
            kieContainerInfo.setContainerAlias((String) container.get("container-alias"));
            results.add(kieContainerInfo);
        }
        return results;
    }

    public KieServerJobStatus stopContainer(String url, String username, String password, String containerId, AddLog workOnGoingView) {
        KieServerJobStatus results = null;
        String completeurl = url + "/containers/" + containerId;
        logger.info("url kie server container : " + completeurl);
        ResponseEntity<KieServerJobStatus> response = restTemplateKiewb
                .execute(completeurl, HttpMethod.DELETE, requestCallback(null, username, password), clientHttpResponse -> {
                    KieServerJobStatus extractedResponse = null;
                    if (clientHttpResponse.getBody() != null) {
                        Scanner s = new Scanner(clientHttpResponse.getBody()).useDelimiter("\\A");
                        String result = s.hasNext() ? s.next() : "";
                        KieServerJobStatus values = mapper.readValue(result, KieServerJobStatus.class);
                        extractedResponse = values;
                    }
                    ResponseEntity<KieServerJobStatus> extractedValue = new ResponseEntity<>(extractedResponse, clientHttpResponse.getHeaders(), clientHttpResponse.getStatusCode());
                    return extractedValue;
                });
        KieServerJobStatus reponseMoteur;
        reponseMoteur = response.getBody();
        workOnGoingView.addRow(reponseMoteur.toString());
        return reponseMoteur;
    }

    public KieContainerInfo createContainer(String url, String username, String password, String containerId, KieContainerRequest request, AddLog workOnGoingView) {
        String completeurl = url + "/containers/" + containerId;
        logger.info("url kie server container : " + completeurl);
        ResponseEntity<Map<String, Object>> response = restTemplateKiewb
                .execute(completeurl, HttpMethod.PUT, requestCallback(request, username, password), clientHttpResponse -> {
                    Map<String, Object> extractedResponse = null;
                    if (clientHttpResponse.getBody() != null) {
                        Scanner s = new Scanner(clientHttpResponse.getBody()).useDelimiter("\\A");
                        String result = s.hasNext() ? s.next() : "";
                        Map<String, Object> map = mapper.readValue(
                                result,
                                new TypeReference<Map<String, Object>>() {
                                });
                        Map<String, Object> values = mapper.readValue(result, Map.class);
                        extractedResponse = values;
                    }
                    ResponseEntity<Map<String, Object>> extractedValue = new ResponseEntity<>(extractedResponse, clientHttpResponse.getHeaders(), clientHttpResponse.getStatusCode());
                    return extractedValue;
                });
        Map<String, Object> reponseMoteur;

        reponseMoteur = response.getBody();
        workOnGoingView.addRow(reponseMoteur.toString());
        KieContainerInfo result = new KieContainerInfo();
        if (reponseMoteur.get("result") != null) {
            String containerID = (String) ((Map) ((Map) reponseMoteur.get("result")).get("kie-container")).get("container-id");
            result.setContainerId(containerID);
        }
        return result;
    }

    private RequestCallback requestCallback(final Object content, String username, String password) {
        return clientHttpRequest -> {
            if (content != null) {
                mapper.writeValue(clientHttpRequest.getBody(), content);
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

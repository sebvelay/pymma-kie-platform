package org.chtijbug.drools.console.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.chtijbug.drools.console.service.model.gitlab.GroupElementResponse;
import org.chtijbug.drools.console.service.model.gitlab.ProjectMoveResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

@Service
public class GitLabRepositoryService {


    private static Logger logger = LoggerFactory.getLogger(GitLabRepositoryService.class);


    private RestTemplate restTemplateKiewb = new RestTemplate();

    private ObjectMapper mapper = new ObjectMapper();

    public List<GroupElementResponse> getListGitRepostitories(String url, String token, String groupName) {

        String completeurl = url + "/groups/" + groupName + "/projects";
        logger.info("url gitlab : " + completeurl);
        ResponseEntity<List<GroupElementResponse>> response = restTemplateKiewb
                .execute(completeurl, HttpMethod.GET, requestCallback(null, token), clientHttpResponse -> {
                    List<GroupElementResponse> extractedResponse = null;
                    if (clientHttpResponse.getBody() != null) {
                        Scanner s = new Scanner(clientHttpResponse.getBody()).useDelimiter("\\A");
                        String result = s.hasNext() ? s.next() : "";
                        GroupElementResponse[] values = mapper.readValue(result, GroupElementResponse[].class);
                        extractedResponse = Arrays.asList(values);
                    }
                    ResponseEntity<List<GroupElementResponse>> extractedValue = new ResponseEntity<>(extractedResponse, clientHttpResponse.getHeaders(), clientHttpResponse.getStatusCode());
                    return extractedValue;
                });
        List<GroupElementResponse> reponseMoteur;

        reponseMoteur = response.getBody();
        return reponseMoteur;
    }

    public GroupElementResponse createProjectForUser(String url, String token, String projectName) {

        String completeurl = url + "/projects?name=" + projectName;
        logger.info("url moteur reco : " + completeurl);
        ResponseEntity<GroupElementResponse> response = restTemplateKiewb
                .execute(completeurl, HttpMethod.POST, requestCallback(null, token), clientHttpResponse -> {
                    GroupElementResponse extractedResponse = null;
                    if (clientHttpResponse.getBody() != null) {
                        Scanner s = new Scanner(clientHttpResponse.getBody()).useDelimiter("\\A");
                        String result = s.hasNext() ? s.next() : "";
                        extractedResponse = mapper.readValue(result, GroupElementResponse.class);
                    }
                    ResponseEntity<GroupElementResponse> extractedValue = new ResponseEntity<>(extractedResponse, clientHttpResponse.getHeaders(), clientHttpResponse.getStatusCode());
                    return extractedValue;
                });
        GroupElementResponse reponseMoteur;

        reponseMoteur = response.getBody();
        return reponseMoteur;
    }

    public ProjectMoveResponse moveProjectTpGroup(String url, String token, String projectid, String groupName) {

        String completeurl = url + "/groups/" + groupName + "/projects/" + projectid;
        logger.info("url moteur reco : " + completeurl);
        ResponseEntity<ProjectMoveResponse> response = restTemplateKiewb
                .execute(completeurl, HttpMethod.POST, requestCallback(null, token), clientHttpResponse -> {
                    ProjectMoveResponse extractedResponse = null;
                    if (clientHttpResponse.getBody() != null) {
                        Scanner s = new Scanner(clientHttpResponse.getBody()).useDelimiter("\\A");
                        String result = s.hasNext() ? s.next() : "";
                        extractedResponse = mapper.readValue(result, ProjectMoveResponse.class);
                    }
                    ResponseEntity<ProjectMoveResponse> extractedValue = new ResponseEntity<>(extractedResponse, clientHttpResponse.getHeaders(), clientHttpResponse.getStatusCode());
                    return extractedValue;
                });
        ProjectMoveResponse reponseMoteur;

        reponseMoteur = response.getBody();
        return reponseMoteur;
    }
///groups/:id/projects/:project_id

    private RequestCallback requestCallback(final Object content, String token) {
        return clientHttpRequest -> {
            if (content != null) {
                mapper.writeValue(clientHttpRequest.getBody(), content);
            }
            clientHttpRequest.getHeaders().add(
                    HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
            clientHttpRequest.getHeaders().add(
                    HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);

            String authHeader = new String(token);
            clientHttpRequest.getHeaders().add(
                    "Private-Token", authHeader);
        };
    }

}

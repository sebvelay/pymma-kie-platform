package org.chtijbug.drools.console.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vaadin.flow.component.UI;
import org.apache.commons.codec.binary.Base64;
import org.chtijbug.drools.console.AddLog;
import org.chtijbug.drools.console.service.model.UserConnected;
import org.chtijbug.drools.console.service.model.kie.JobStatus;
import org.chtijbug.drools.proxy.persistence.model.User;
import org.chtijbug.drools.proxy.persistence.repository.UserRepository;
import org.chtijbug.guvnor.server.jaxrs.api.UserLoginInformation;
import org.chtijbug.guvnor.server.jaxrs.jaxb.Asset;
import org.chtijbug.guvnor.server.jaxrs.model.PlatformProjectResponse;
import org.drools.workbench.models.guided.template.backend.RuleTemplateModelXMLPersistenceImpl;
import org.drools.workbench.models.guided.template.shared.TemplateModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.util.*;

@Service
@DependsOn("applicationContext")
public class KieRepositoryService {


    private static Logger logger = LoggerFactory.getLogger(KieRepositoryService.class);

    @Autowired
    private UserRepository userRepository;

    private RestTemplate restTemplateKiewb = new RestTemplate();

    private ObjectMapper mapper = new ObjectMapper();

    private String pojoToStringMethod(String assetContent, List<HashMap<String, Object>> objects) {

        TemplateModel model = RuleTemplateModelXMLPersistenceImpl.getInstance().unmarshal(assetContent);
        int i = 0;
        model.clearRows();
        for (HashMap<String, Object> t : objects) {

            List<String> row = new ArrayList<>();
            for (Map.Entry<String, Object> entry : t.entrySet()) {
                row.add(String.valueOf(entry.getValue()));
            }
            model.addRow(i, row.toArray(new String[row.size()]));
            i++;
        }


        return RuleTemplateModelXMLPersistenceImpl.getInstance().marshal(model);
    }

    public void updateAssetSource(String url, String username, String password, String spaceName, String projectName, String assetName, String assetSource) {

        String assetContent = getAssetSource(url,
                username,
                password,
                spaceName,
                projectName,
                assetName);


        String completeurl = url + "/chtijbug/" + spaceName + "/" + projectName + "/asset/" + assetName + "/source";
        logger.info("url moteur reco : " + completeurl);

        ResponseEntity response = restTemplateKiewb
                .execute(completeurl, HttpMethod.POST, requestCallback(assetSource, username, password), clientHttpResponse -> {
                    String extractedResponse = null;
                    if (clientHttpResponse.getBody() != null) {
                        Scanner s = new Scanner(clientHttpResponse.getBody()).useDelimiter("\\A");
                        String result = s.hasNext() ? s.next() : "";
                        extractedResponse = result;
                    }
                    ResponseEntity extractedValue = new ResponseEntity<>(extractedResponse, clientHttpResponse.getHeaders(), clientHttpResponse.getStatusCode());
                    return extractedValue;
                });

        // restTemplateKiewb.exchange(completeurl, HttpMethod.POST, requestCallBack(content, username, password), void.class);
        System.out.println("");
    }

    public String getAssetSource(String url, String username, String password, String spaceName, String projectName, String assetName) {
        String completeurl = url + "/chtijbug/" + spaceName + "/" + projectName + "/assets/" + assetName + "/source";
        logger.info("url moteur reco : " + completeurl);
        ResponseEntity<String> response = restTemplateKiewb
                .execute(completeurl, HttpMethod.GET, requestCallback(null, username, password), clientHttpResponse -> {
                    String extractedResponse = null;
                    if (clientHttpResponse.getBody() != null) {
                        Scanner s = new Scanner(clientHttpResponse.getBody()).useDelimiter("\\A");
                        String result = s.hasNext() ? s.next() : "";
                        extractedResponse = result;
                    }
                    ResponseEntity<String> extractedValue = new ResponseEntity<>(extractedResponse, clientHttpResponse.getHeaders(), clientHttpResponse.getStatusCode());
                    return extractedValue;
                });
        String reponseMoteur;

        reponseMoteur = response.getBody();
        return reponseMoteur;
    }

    public List<PlatformProjectResponse> getListSpaces2(String url, String username, String password) {
        String completeurl = url + "/chtijbug/detailedSpaces";
        logger.info("url moteur reco : " + completeurl);
        ResponseEntity<List<PlatformProjectResponse>> response = restTemplateKiewb
                .execute(completeurl, HttpMethod.GET, requestCallback(null, username, password), clientHttpResponse -> {
                    List<PlatformProjectResponse> extractedResponse = null;
                    if (clientHttpResponse.getBody() != null) {
                        Scanner s = new Scanner(clientHttpResponse.getBody()).useDelimiter("\\A");
                        String result = s.hasNext() ? s.next() : "";
                        PlatformProjectResponse[] values = mapper.readValue(result, PlatformProjectResponse[].class);
                        extractedResponse = Arrays.asList(values);
                    }
                    ResponseEntity<List<PlatformProjectResponse>> extractedValue = new ResponseEntity<>(extractedResponse, clientHttpResponse.getHeaders(), clientHttpResponse.getStatusCode());
                    return extractedValue;
                });
        List<PlatformProjectResponse> reponseMoteur;

        reponseMoteur = response.getBody();
        return reponseMoteur;
    }

    public UserConnected login(String url, String username, String password,String workbenchName) {

        User user = userRepository.findByLogin(username);
        String completeurl = url + "/chtijbug/login";
        if (user != null && user.getPassword().equals(password)) {
            if (user.getCustomer()!= null &&
                    user.getCustomer().getKieWorkbench()!= null
                    && user.getCustomer().getKieWorkbench().getInternalUrl()!= null){
                completeurl = user.getCustomer().getKieWorkbench().getInternalUrl()+"/rest/chtijbug/login";
            }

            logger.info("url moteur reco : " + completeurl);
            ResponseEntity<UserLoginInformation> response = restTemplateKiewb
                    .execute(completeurl, HttpMethod.GET, requestCallback(null, username, password), clientHttpResponse -> {
                        UserLoginInformation extractedResponse = null;
                        if (clientHttpResponse.getBody() != null) {
                            Scanner s = new Scanner(clientHttpResponse.getBody()).useDelimiter("\\A");
                            String result = s.hasNext() ? s.next() : "";
                            extractedResponse = mapper.readValue(result, UserLoginInformation.class);

                        }
                        ResponseEntity<UserLoginInformation> extractedValue = new ResponseEntity<>(extractedResponse, clientHttpResponse.getHeaders(), clientHttpResponse.getStatusCode());
                        return extractedValue;
                    });
            UserConnected userConnected = new UserConnected();

            UserLoginInformation responseBody = response.getBody();
            userConnected.setUserName(username);
            userConnected.setUserPassword(password);
            userConnected.setUserPassword(password);
            userConnected.getProjectResponses().addAll(responseBody.getProjects());
            userConnected.getRoles().addAll(responseBody.getRoles());
            userConnected.setUserName(username);
            userConnected.setKieWorkbenchName(workbenchName);
            return userConnected;
        } else {
            return null;
        }
    }

    public List<Asset> getListAssets(String url, String username, String password, String spaceName, String projectName) {
        String completeurl = url + "/chtijbug/" + spaceName + "/" + projectName + "/assets";
        logger.info("url moteur reco : " + completeurl);
        ResponseEntity<List<Asset>> response = restTemplateKiewb
                .execute(completeurl, HttpMethod.GET, requestCallback(null, username, password), clientHttpResponse -> {
                    List<Asset> extractedResponse = null;
                    if (clientHttpResponse.getBody() != null) {
                        Scanner s = new Scanner(clientHttpResponse.getBody()).useDelimiter("\\A");
                        String result = s.hasNext() ? s.next() : "";
                        Asset[] values = mapper.readValue(result, Asset[].class);
                        extractedResponse = Arrays.asList(values);
                    }
                    ResponseEntity<List<Asset>> extractedValue = new ResponseEntity<>(extractedResponse, clientHttpResponse.getHeaders(), clientHttpResponse.getStatusCode());
                    return extractedValue;
                });
        List<Asset> reponseMoteur;

        reponseMoteur = response.getBody();
        return reponseMoteur;
    }

    public JobStatus buildProject(String url, String username, String password, String space, String project, String branchName,String command, AddLog workOnGoingView, UI ui) {
        String completeurl= url + "/spaces/" + space + "/projects/" + project + "/maven/" + command;
        if (branchName!= null && branchName.length()>0){
            completeurl= url + "/spaces/" + space + "/projects/" + project +"/branches/"+branchName+ "/maven/" + command;
        }
        logger.info("url Maven install : " + completeurl);
        ResponseEntity<JobStatus> response = restTemplateKiewb
                .execute(completeurl, HttpMethod.POST, requestCallback(null, username, password), clientHttpResponse -> {
                    JobStatus extractedResponse = null;
                    if (clientHttpResponse.getBody() != null) {
                        Scanner s = new Scanner(clientHttpResponse.getBody()).useDelimiter("\\A");
                        String result = s.hasNext() ? s.next() : "";
                        JobStatus values = mapper.readValue(result, JobStatus.class);
                        extractedResponse = values;
                    }
                    ResponseEntity<JobStatus> extractedValue = new ResponseEntity<>(extractedResponse, clientHttpResponse.getHeaders(), clientHttpResponse.getStatusCode());
                    return extractedValue;
                });
        JobStatus reponseMoteur;

        reponseMoteur = response.getBody();
        workOnGoingView.addRow(reponseMoteur.toString(), ui);
        return reponseMoteur;
    }

    public JobStatus getStatusJobID(String url, String username, String password, String jobID) {

        String completeurl = url + "/jobs/" + jobID;
        logger.info("url moteur reco : " + completeurl);
        ResponseEntity<JobStatus> response = restTemplateKiewb
                .execute(completeurl, HttpMethod.GET, requestCallback(null, username, password), clientHttpResponse -> {
                    JobStatus extractedResponse = null;
                    if (clientHttpResponse.getBody() != null) {
                        Scanner s = new Scanner(clientHttpResponse.getBody()).useDelimiter("\\A");
                        String result = s.hasNext() ? s.next() : "";
                        extractedResponse = mapper.readValue(result, JobStatus.class);

                    }
                    ResponseEntity<JobStatus> extractedValue = new ResponseEntity<>(extractedResponse, clientHttpResponse.getHeaders(), clientHttpResponse.getStatusCode());
                    return extractedValue;
                });
        JobStatus reponseMoteur;

        reponseMoteur = response.getBody();
        return reponseMoteur;
    }

    private HttpEntity requestCallBack(final Object content, String username, String password) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(
                HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        httpHeaders.add(
                HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        String auth = username + ":" + password;
        byte[] encodedAuth = Base64.encodeBase64(
                auth.getBytes(Charset.forName("UTF-8")));
        String authHeader = "Basic " + new String(encodedAuth);
        httpHeaders.add(
                HttpHeaders.AUTHORIZATION, authHeader);
        HttpEntity httpEntity = new HttpEntity(content, httpHeaders);


        return httpEntity;
    }

    private RequestCallback requestCallback(final Object content, String username, String password) {
        return clientHttpRequest -> {
            if (content != null) {
                if (content instanceof String) {
                    String stringContent = (String) content;
                    stringContent = stringContent.replace("\"", "");
                    mapper.writeValue(clientHttpRequest.getBody(), stringContent);
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

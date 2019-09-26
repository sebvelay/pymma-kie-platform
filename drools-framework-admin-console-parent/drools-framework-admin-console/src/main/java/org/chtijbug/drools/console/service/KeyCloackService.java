package org.chtijbug.drools.console.service;

import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;


public class KeyCloackService {

    private String serverUrl;
    private String serverRealm;
    private String serverUserName;
    private String serverPassword;
    private String serverClientId;

    private Keycloak keycloak;

    public KeyCloackService(String serverUrl, String serverRealm, String serverUserName, String serverPassword, String serverClientId) {
        this.serverUrl = serverUrl;
        this.serverRealm = serverRealm;
        this.serverUserName = serverUserName;
        this.serverPassword = serverPassword;
        this.serverClientId = serverClientId;
        this.initConnection();
    }

    private void initConnection(){
         keycloak = KeycloakBuilder
                .builder()
                .serverUrl(serverUrl)
                .realm(serverRealm)
                .username(serverUserName)
                .password(serverPassword)
                .clientId(serverClientId)
                .resteasyClient(new ResteasyClientBuilder().connectionPoolSize(10).build())
                .build();

/**
        CredentialRepresentation credentialRepresentation = new CredentialRepresentation();
        credentialRepresentation.setType(CredentialRepresentation.PASSWORD);
        credentialRepresentation.setValue("12345678");

        UserRepresentation userRepresentation = new UserRepresentation();
        userRepresentation.setUsername("lupin");
        userRepresentation.setFirstName("Remus");
        userRepresentation.setLastName("Lupin");
        userRepresentation.setEnabled(true);
        userRepresentation.setCredentials(Arrays.asList(credentialRepresentation));
        //keycloak.realm(REALM).users().create(userRepresentation);

        UsersResource usersResource = keycloak.realm(serverRealm).users();
        UserRepresentation user = usersResource.search("lupin").get(0);
        user.setEmail("lupin@hogwarts.co.uk");
        usersResource.get(user.getId()).update(user);
**/

    }




}

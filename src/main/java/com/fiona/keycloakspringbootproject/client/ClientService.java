package com.fiona.keycloakspringbootproject.client;

import com.fasterxml.jackson.annotation.JacksonInject;
import lombok.AllArgsConstructor;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.representations.AccessTokenResponse;
import org.keycloak.representations.idm.ClientRepresentation;
import org.keycloak.admin.client.resource.RealmResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;

@Service
public class ClientService {

    @Autowired
    private RestTemplate restTemplate;
    @Value("${keycloak.auth-server-url}")
    private String keyCloakServerUrl;

    Keycloak keycloak = KeycloakBuilder.builder()
            .serverUrl("http://localhost:8180/auth")
            .grantType(OAuth2Constants.PASSWORD)
            .realm("master")
            .clientId("admin-cli")
            .username("admin_user")
            .password("admin_password")
            .resteasyClient(
                    new ResteasyClientBuilder()
                            .connectionPoolSize(10).build()
            ).build();

//private final keycloak.tokenManager().getAccessToken();
AccessTokenResponse tokenResponse = keycloak.tokenManager().getAccessToken();

    String accessToken = tokenResponse.getToken();

    RealmResource realmResource = keycloak.realm("master");


    private HttpHeaders createHeaders(String authorizationHeader) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.AUTHORIZATION, authorizationHeader);
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }

    public void createClient(ClientRepresentation clientRepresentation,String authorizationHeader){
        HttpHeaders headers = createHeaders(authorizationHeader);

        RealmResource realmResource = keycloak.realm("master");
        realmResource.clients().create(clientRepresentation);
    }
    public List<ClientRepresentation> getAllClients() {
        RealmResource realmResource = keycloak.realm("master");
        return realmResource.clients().findAll();
    }

}

package com.fiona.keycloakspringbootproject.realm;

import org.keycloak.representations.idm.RealmRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class realmService {
    private final RestTemplate restTemplate =new RestTemplate();
    @Value("${keycloak.auth-server-url}")
    private String apiUrl;

    private HttpHeaders createHeaders(String authorizationHeader) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.AUTHORIZATION, authorizationHeader);
        headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        return headers;
    }

    public RealmRepresentation createRealm( RealmRepresentation realm,String authorizationHeader) {

        HttpHeaders headers = createHeaders(authorizationHeader);
        String url = apiUrl+"/admin" + "/realms";
        ResponseEntity<RealmRepresentation> response = restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<>(realm, headers), RealmRepresentation.class);

        return response.getBody();
    }
    public RealmRepresentation getRealmByName(String authorizationHeader, String realmName) {
        HttpHeaders headers = createHeaders(authorizationHeader);

        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

        String url = apiUrl+"/realms/"+realmName;
        ResponseEntity<RealmRepresentation> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, RealmRepresentation.class);

        return responseEntity.getBody();
    }

    public RealmRepresentation updateRealm(String realmName, RealmRepresentation realm,String authorizationHeader ) {
        String url = apiUrl + "/realms/" + realmName;
        HttpHeaders headers = createHeaders(authorizationHeader);
        HttpEntity<RealmRepresentation> requestEntity = new HttpEntity<>(realm, headers);
        ResponseEntity<RealmRepresentation> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, RealmRepresentation.class);
        return responseEntity.getBody();
    }

    public void deleteRealm(String authorizationHeader, String realmName) {
        HttpHeaders headers = createHeaders(authorizationHeader);

        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

        String url = apiUrl + "/realms/" + realmName;
        restTemplate.exchange(url, HttpMethod.DELETE, requestEntity, Void.class);
    }


}

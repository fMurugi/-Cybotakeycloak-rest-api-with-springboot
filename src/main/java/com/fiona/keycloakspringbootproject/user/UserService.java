package com.fiona.keycloakspringbootproject.user;

import lombok.AllArgsConstructor;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.security.Key;
import java.util.*;

@Service
public class UserService {

    @Autowired
    private RestTemplate restTemplate;
    @Value("${keycloak.auth-server-url}")
    private  String KeycloakServerUrl ;

    private HttpHeaders createHeaders(String authorizationHeader) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.AUTHORIZATION, authorizationHeader);
        return headers;
    }

    public void createUser(UserRepresentation user, String authorizationHeader){
        HttpHeaders headers = createHeaders(authorizationHeader);

        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<UserRepresentation> requestEntity = new HttpEntity<>(user, headers);

        String url = KeycloakServerUrl +"/admin"+"/realms/" + "master" + "/users";
        restTemplate.postForObject(url, requestEntity, String.class);
    }

    public void assignUserRole(String userId, RoleRepresentation role, String authorizationHeader) {
        HttpHeaders headers = createHeaders(authorizationHeader);
        headers.setContentType(MediaType.APPLICATION_JSON);

        List<RoleRepresentation> rolesList = Collections.singletonList(role);
        Map<String, List<RoleRepresentation>> rolesMap = new HashMap<>();
        rolesMap.put("roles", rolesList);

        HttpEntity<Map<String, List<RoleRepresentation>>> requestEntity = new HttpEntity<>(rolesMap, headers);

        String url = KeycloakServerUrl + "/realms/" + "master" + "/users/" + userId + "/role-mappings/clients" +"/1ff75cf3-99dc-4cc3-80ba-e48350f97eb0";
        restTemplate.postForObject(url, requestEntity, String.class);
    }

    //get all users
    public List<UserRepresentation> getAllUsers(String realmName, String authorizationHeader) {
        HttpHeaders headers = createHeaders(authorizationHeader);
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

        String url = KeycloakServerUrl + "/admin/realms/" + realmName + "/users";
        UserRepresentation[] usersArray = restTemplate.exchange(url, HttpMethod.GET, requestEntity, UserRepresentation[].class).getBody();

        if (usersArray != null) {
            return Arrays.asList(usersArray);
        } else {
            return Collections.emptyList();
        }
    }




}

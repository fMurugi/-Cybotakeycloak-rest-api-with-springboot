package com.fiona.keycloakspringbootproject.role;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class RoleService {
    @Autowired
    private  RestTemplate restTemplate;
//    @Value("${keycloak.auth-server-url}")
    private String keyCloakServerUrl= "http://localhost:8181/auth/admin/";



    public Map<String, String> getRoles(String realmName, String authorizationHeader) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.AUTHORIZATION, authorizationHeader);
        headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);

        String url = keyCloakServerUrl  + realmName +"/clients"+"/1ff75cf3-99dc-4cc3-80ba-e48350f97eb0"+"/roles";
        ResponseEntity<Map> response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<>(headers), Map.class);

        return response.getBody();
    }

}

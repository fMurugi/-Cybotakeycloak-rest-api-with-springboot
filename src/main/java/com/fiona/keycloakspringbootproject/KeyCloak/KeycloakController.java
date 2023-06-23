//package com.fiona.keycloakspringbootproject.KeyCloak;
//
//import org.keycloak.representations.idm.RealmRepresentation;
//import org.keycloak.representations.idm.UserRepresentation;
//import org.springframework.http.*;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.client.RestTemplate;
//
//@RestController
//public class KeycloakController {
//    private final  RestTemplate restTemplate = new RestTemplate();
//
//    private String apiUrl="http://localhost:8181/auth/admin";
////"http://localhost:8181/auth/admin/realms/master/users
//    @GetMapping("/users")
//    @ResponseBody
//    public ResponseEntity<String> getUsers(@RequestHeader("Authorization") String authorizationHeader) {
//        HttpHeaders headers = new HttpHeaders();
//        headers.set(HttpHeaders.AUTHORIZATION, authorizationHeader);
//        headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
//
//        String url = apiUrl+"/realms/master/users";
//        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<>(headers), String.class);
//
//        return response;
//    }
//
//    @PostMapping("/realms")
//    @ResponseBody
//    public ResponseEntity<String> createRealm( @RequestHeader("Authorization") String authorizationHeader,@RequestBody RealmRepresentation realm) {
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.set(HttpHeaders.AUTHORIZATION,authorizationHeader);
//        headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
//        headers.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
//
//        String url = "http://localhost:8181/auth/admin/realms";
//        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<>(realm, headers), String.class);
//
//        return response;
//    }
//
//    @PostMapping("/create_user")
//    @ResponseBody
//    public ResponseEntity<String> createUser(@RequestHeader("Authorization") String authorizationHeader,
//                                             @RequestBody UserRepresentation user) {
//        HttpHeaders headers = new HttpHeaders();
//        headers.set(HttpHeaders.AUTHORIZATION, authorizationHeader);
//        headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
//        headers.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
//
//        String url = "http://localhost:8181/auth/admin/realms/Test-realm/users";
//        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<>(user, headers), String.class);
//
//        return response;
//    }
//
//
//
//
//
//
//
//
//}

//package com.fiona.keycloakspringbootproject.Config;
//
//
//import org.keycloak.admin.client.Keycloak;
//import org.keycloak.admin.client.KeycloakBuilder;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class KeyCloakConfig {
//    @Value("${keycloak.auth-server-url}")
//    private String keycloakServerUrl;
//    @Value("${keycloak.realm}")
//    private String realm;
//    @Value("${keycloak.resource}")
//    private String clientId;
//    @Value("${keycloak.credentials.username}")
//    private String username;
//    @Value("${keycloak.credentials.password}")
//    private String password;
//    @Value("${keycloak.grant-type}")
//    private String grantType;
//
//
//    @Bean
//    public Keycloak keycloak(){
//        return KeycloakBuilder.builder()
//                .serverUrl(keycloakServerUrl)
//                .realm(realm)
//                .username(username)
//                .password(password)
//                .clientId(clientId)
//                .build();
//    }
//
//
//
//}

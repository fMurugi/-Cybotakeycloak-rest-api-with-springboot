package com.fiona.keycloakspringbootproject.role;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/roles")
@AllArgsConstructor
public class RoleController {
    private RoleService roleService;
    @GetMapping
    public ResponseEntity<Map<String, String>> getRoles(@RequestHeader("Authorization") String authorizationHeader) {
        String realmName = "master";
        Map<String, String> roles = roleService.getRoles(realmName, authorizationHeader);
        return new ResponseEntity<>(roles, HttpStatus.OK);
    }

}

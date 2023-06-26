package com.fiona.keycloakspringbootproject.user;

import lombok.AllArgsConstructor;
import org.keycloak.jose.jwk.JWK;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.context.annotation.Role;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    private    UserService userService;

    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody UserRepresentation user,
                                             @RequestHeader("Authorization") String authorizationHeader) {
        userService.createUser(user, authorizationHeader);
        return ResponseEntity.ok("User created successfully.");
    }

    @PostMapping("/{userId}/roles")
    public ResponseEntity<String> assignUserRole(@PathVariable("userId") String userId,
                                                 @RequestHeader("Authorization") String authorizationHeader,
                                                 @RequestBody RoleRepresentation payload) {
        RoleRepresentation role = new RoleRepresentation();
        role.setName(payload.getName());
        // Set other properties of the role if needed

        userService.assignUserRole(userId, role, authorizationHeader);
        return ResponseEntity.ok("User role assigned successfully.");
    }

    @GetMapping("/{realmName}")
    public ResponseEntity<List<UserRepresentation>> getAllUsers(@PathVariable("realmName") String realmName,
                                                                @RequestHeader("Authorization") String authorizationHeader){
        List<UserRepresentation> users = userService.getAllUsers(realmName,authorizationHeader);
        return ResponseEntity.ok(users);
    }




    @GetMapping("/secured")
    public ResponseEntity<String> securedEndpoint() {
        return ResponseEntity.ok("This is a secured endpoint accessible only by users with the 'ROLE_ADMIN' role.");
    }

    @GetMapping("/member")
    @RolesAllowed("user")
    public ResponseEntity<String> getMember() {
        return ResponseEntity.ok("Hello Member");
    }
    @GetMapping("/admin")
    @RolesAllowed("admin")
    public ResponseEntity<String> getAdmin() {
        return ResponseEntity.ok("Hello Admin");
    }
}

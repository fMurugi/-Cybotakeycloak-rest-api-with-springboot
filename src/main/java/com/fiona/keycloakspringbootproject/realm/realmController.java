package com.fiona.keycloakspringbootproject.realm;

import lombok.AllArgsConstructor;
import org.keycloak.representations.idm.RealmRepresentation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/realms")
@AllArgsConstructor
public class realmController {

    private  realmService realmService;

    @PostMapping
    public ResponseEntity<RealmRepresentation> createRealm( @RequestHeader("Authorization") String authorizationHeader,@RequestBody RealmRepresentation realm) {
        RealmRepresentation createdRealm = realmService.createRealm(realm,authorizationHeader);
        return new ResponseEntity<>(createdRealm, HttpStatus.CREATED);
    }

    @GetMapping("/{realmName}")
    public ResponseEntity<RealmRepresentation> getRealmByName(@PathVariable("realmName") String realmName,@RequestHeader("Authorization") String authorizationHeader) {
        RealmRepresentation realm = realmService.getRealmByName(realmName,authorizationHeader);
        return new ResponseEntity<>(realm, HttpStatus.OK);
    }

    @PutMapping("/{realmName}")
    public ResponseEntity<RealmRepresentation> updateRealm(@PathVariable("realmName") String realmName, @RequestBody RealmRepresentation realm,@RequestHeader("Authorization") String authorizationHeader) {
        RealmRepresentation updatedRealm=realmService.updateRealm(realmName, realm,authorizationHeader);
        return new ResponseEntity<>(updatedRealm,HttpStatus.OK);
    }

    @DeleteMapping("/{realmName}")
    public ResponseEntity<Void> deleteRealm(@PathVariable("realmName") String realmName,@RequestHeader("Authorization") String authorizationHeader) {
       realmService.deleteRealm(realmName,authorizationHeader);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

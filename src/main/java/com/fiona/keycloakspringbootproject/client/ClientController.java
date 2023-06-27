package com.fiona.keycloakspringbootproject.client;

import lombok.AllArgsConstructor;

import org.keycloak.representations.idm.ClientRepresentation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Path;
import java.util.List;

@RestController
@RequestMapping("/api/v1/clients")
@AllArgsConstructor
public class ClientController {
    private ClientService clientService;

    @PostMapping
    public ResponseEntity<String> createClient(
            @RequestBody ClientRepresentation clientRepresentation,
            @RequestHeader("Authorization") String authorizationHeader) {
        clientService.createClient(clientRepresentation, authorizationHeader);
        return ResponseEntity.status(HttpStatus.CREATED).body("Client created successfully");
    }
    @GetMapping("/get_all_clients")
    public ResponseEntity<List<ClientRepresentation>> getAllClients() {
        List<ClientRepresentation> clients = clientService.getAllClients();
        return new ResponseEntity<>(clients, HttpStatus.OK);
    }

}

package com.example.auth.controllers;

import com.example.auth.domain.user.ClientePerfilDTO;
import com.example.auth.infra.security.TokenService;
import com.example.auth.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("cliente")
public class ClienteController {
  @Autowired
  private AuthenticationManager authenticationManager;
  @Autowired
  private ClienteRepository repository;
  @Autowired
  private TokenService tokenService;

  @GetMapping("perfil")
  public ResponseEntity<ClientePerfilDTO> getPerfil(@RequestHeader("Authorization") String token
  ) {
    // Remove "Bearer " prefix if present
    token = token.replace("Bearer ", "");

    // Validate the token and get the username
    String username = tokenService.validateToken(token);

    if (username.isEmpty()) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    // Find the user by username
    UserDetails user = repository.findByLogin(username);

    if (user == null) {
      return ResponseEntity.notFound().build();
    }

    // Return a DTO with limited user information
    ClientePerfilDTO profile = new ClientePerfilDTO(user);

    return ResponseEntity.ok(profile);
  }

  @GetMapping("detalhes")
  public ResponseEntity<ClientePerfilDTO> getDetalhes(@RequestHeader("Authorization") String token
  ) {
    // Remove "Bearer " prefix if present
    token = token.replace("Bearer ", "");

    // Validate the token and get the username
    String username = tokenService.validateToken(token);

    if (username.isEmpty()) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    // Find the user by username
    UserDetails user = repository.findByLogin(username);

    if (user == null) {
      return ResponseEntity.notFound().build();
    }

    // Return a DTO with limited user information
    ClientePerfilDTO profile = new ClientePerfilDTO(user);

    return ResponseEntity.ok(profile);
  }
}

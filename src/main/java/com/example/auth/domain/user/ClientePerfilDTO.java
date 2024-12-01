package com.example.auth.domain.user;

import org.springframework.security.core.userdetails.UserDetails;

public record ClientePerfilDTO(String id, String login) {
  public ClientePerfilDTO(UserDetails cliente) {
    this(
      cliente instanceof Cliente ? ((Cliente) cliente).getId() : null,
      cliente.getUsername());
  }
}

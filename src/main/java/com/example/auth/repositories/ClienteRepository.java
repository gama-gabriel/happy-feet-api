package com.example.auth.repositories;

import com.example.auth.domain.user.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface ClienteRepository extends JpaRepository<Cliente, String> {
    UserDetails findByLogin(String login);
}

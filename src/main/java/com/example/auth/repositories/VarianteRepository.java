package com.example.auth.repositories;

import com.example.auth.domain.variante.Variante;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VarianteRepository extends JpaRepository<Variante, Integer> {
}

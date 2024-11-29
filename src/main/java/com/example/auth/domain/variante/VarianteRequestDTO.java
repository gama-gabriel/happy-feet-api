package com.example.auth.domain.variante;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record VarianteRequestDTO(
  @NotBlank
  String nomeCor,
  @NotBlank
  String corHex,

  @NotNull
    Integer idProduto
) {}

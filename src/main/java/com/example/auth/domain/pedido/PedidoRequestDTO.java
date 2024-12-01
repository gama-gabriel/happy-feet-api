package com.example.auth.domain.pedido;

import jakarta.validation.constraints.NotNull;

import java.time.ZonedDateTime;

public record PedidoRequestDTO(
  @NotNull
  String idCliente,
  @NotNull
  Integer idVariante,
  @NotNull
  Float total,
  ZonedDateTime dataCompra,
  String status,
  String enderecoEntrega,
  String metodoPagamento,
  String codigoRastreio
) {
}

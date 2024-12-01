package com.example.auth.domain.pedido;

import java.time.ZonedDateTime;

public record PedidoResponseDTO(Integer id, String idCliente, Integer idVariante, Float total, ZonedDateTime dataCompra, String status, String metodoPagamento, String enderecoEntrega, String codigoRastreio) {
  public PedidoResponseDTO(Pedido pedido) {
    this(pedido.getId(), pedido.getCliente().getId(), pedido.getVariante().getId(), pedido.getTotal(), pedido.getDataCompra(), pedido.getStatus(), pedido.getMetodoPagamento(), pedido.getEnderecoEntrega(), pedido.getCodigoRastreio());
  }
}

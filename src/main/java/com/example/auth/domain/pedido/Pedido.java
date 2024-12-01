package com.example.auth.domain.pedido;

import com.example.auth.domain.user.Cliente;
import com.example.auth.domain.variante.Variante;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;

@Table(name="pedido")
@Entity(name="pedido")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Pedido {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pedido_seq")
  @SequenceGenerator(name = "pedido_seq", sequenceName = "pedido_seq", allocationSize = 1)
  private Integer id;

  @JsonBackReference
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "id_variante", nullable = false)
  private Variante variante;

  @JsonBackReference
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "id_cliente", nullable = false)
  private Cliente cliente;

  private Float total;
  private ZonedDateTime dataCompra;
  private String status;
  private String enderecoEntrega;
  private String metodoPagamento;
  private String codigoRastreio;

  public Pedido(PedidoRequestDTO data) {
    this.total = data.total();
    this.dataCompra = ZonedDateTime.now();
    this.status = data.status();
    this.enderecoEntrega = data.enderecoEntrega();
    this.metodoPagamento = data.metodoPagamento();
    this.codigoRastreio = data.codigoRastreio();
  }
}

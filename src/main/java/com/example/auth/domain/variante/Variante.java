package com.example.auth.domain.variante;

import com.example.auth.domain.produto.Produto;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name="variante")
@Entity(name="variante")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Variante {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "variante_seq")
  @SequenceGenerator(name = "variante_seq", sequenceName = "variante_seq", allocationSize = 1)
  private int id;

  @JsonBackReference
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "id_produto", nullable = false)
  private Produto produto;
  private String nomeCor;
  private String corHex;

  public Variante (VarianteRequestDTO data) {
    this.nomeCor = data.nomeCor();
    this.corHex = data.corHex();
  }
}

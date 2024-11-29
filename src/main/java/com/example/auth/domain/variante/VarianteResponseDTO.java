package com.example.auth.domain.variante;

import com.example.auth.domain.produto.Produto;

public record VarianteResponseDTO(Integer id, String nomeCor, String corHex, Integer id_produto) {
 public VarianteResponseDTO(Variante variante) {
   this(variante.getId(), variante.getNomeCor(), variante.getCorHex(), variante.getProduto().getId());
 }
}

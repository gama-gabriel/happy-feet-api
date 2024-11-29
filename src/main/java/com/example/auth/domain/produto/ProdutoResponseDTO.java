package com.example.auth.domain.produto;

import com.example.auth.domain.variante.Variante;

import java.util.List;

public record ProdutoResponseDTO(
  Integer id,
  String nome,
  String categoria,
  String marca,
  String keywords,
  String descricao,
  float preco,
  GeneroProduto genero,
  List<Variante> variantes
) {
    public ProdutoResponseDTO(Produto produto) {
    this(
      produto.getId(),
      produto.getNome(),
      produto.getCategoria(),
      produto.getMarca(),
      produto.getKeywords(),
      produto.getDescricao(),
      produto.getPreco(),
      produto.getGenero(),
      produto.getVariantes()
    );
  }
}

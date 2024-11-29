package com.example.auth.domain.produto;

import com.example.auth.domain.variante.VarianteRequestDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record ProdutoRequestDTO(
        @NotBlank
        String nome,
        @NotBlank
        String marca,
        @NotBlank
        String keywords,
        @NotBlank
        String categoria,
        String descricao,
        @NotNull
        float preco,
        @NotNull
        GeneroProduto genero,
        @NotNull
        List<VarianteRequestDTO> variantes
) {
}

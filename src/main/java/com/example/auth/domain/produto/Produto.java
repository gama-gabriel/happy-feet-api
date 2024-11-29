package com.example.auth.domain.produto;

import com.example.auth.domain.variante.Variante;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Table(name = "produto")
@Entity(name = "produto")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "produto_seq")
    @SequenceGenerator(name = "produto_seq", sequenceName = "produto_seq", allocationSize = 1)
    private Integer id;
    private String nome;
    private String marca;
    private String keywords;
    private String categoria;
    private String descricao;
    private float preco;
    @Enumerated(EnumType.STRING)
    private GeneroProduto genero;

    @JsonManagedReference
    @OneToMany(mappedBy = "produto", cascade = CascadeType.ALL)
    private List<Variante> variantes;

    public Produto(ProdutoRequestDTO data){
        this.nome = data.nome();
        this.marca = data.marca();
        this.keywords = data.keywords();
        this.categoria = data.categoria();
        this.descricao = data.descricao();
        this.preco = data.preco();
        this.genero = data.genero();
    }
}

package com.example.auth.controllers;

import com.example.auth.domain.produto.Produto;
import com.example.auth.domain.variante.Variante;
import com.example.auth.domain.variante.VarianteRequestDTO;
import com.example.auth.domain.variante.VarianteResponseDTO;
import com.example.auth.repositories.ProdutoRepository;
import com.example.auth.repositories.VarianteRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController()
@RequestMapping("variante")
public class VarianteController {
  @Autowired
  VarianteRepository repository;
  @Autowired
  ProdutoRepository produtoRepository;

  @PostMapping
  public ResponseEntity postVariante(@RequestBody @Valid VarianteRequestDTO body) {
    Optional<Produto> produto = this.produtoRepository.findById(body.idProduto());
    if (produto.isEmpty()) {
      return new ResponseEntity<>("Produto not found", HttpStatus.BAD_REQUEST);
    }

    Variante newVariante = new Variante(body);
    newVariante.setProduto(produto.get());

    this.repository.save(newVariante);
    return ResponseEntity.ok().build();
  }

  @GetMapping("all")
  public ResponseEntity<List<VarianteResponseDTO>> getAllVariantes() {
    List<VarianteResponseDTO> varianteList = this.repository.findAll().stream().map(VarianteResponseDTO::new).toList();
    return ResponseEntity.ok(varianteList);
  }
  @GetMapping
  public ResponseEntity<VarianteResponseDTO> getVariante(@RequestParam Integer id) {
    Optional<Variante> variante = repository.findById(id);
    if (variante.isPresent()) {
      return ResponseEntity.ok(new VarianteResponseDTO(variante.get()));
    }
    return null;
  }
}

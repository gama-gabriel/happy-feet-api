package com.example.auth.controllers;

import com.example.auth.domain.produto.Produto;
import com.example.auth.domain.produto.ProdutoRequestDTO;
import com.example.auth.domain.produto.ProdutoResponseDTO;
import com.example.auth.repositories.ProdutoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("produto")
public class ProdutoController {
    @Autowired
    ProdutoRepository repository;

    @PostMapping
    public ResponseEntity postProduct(@RequestBody @Valid ProdutoRequestDTO body){
        Produto newProduto = new Produto(body);

        this.repository.save(newProduto);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity getAllProducts(){
        List<ProdutoResponseDTO> productList = this.repository.findAll().stream().map(ProdutoResponseDTO::new).toList();

        return ResponseEntity.ok(productList);
    }

    @GetMapping("/hello")
    public String oi() {
      return "Hello World";
    }
}

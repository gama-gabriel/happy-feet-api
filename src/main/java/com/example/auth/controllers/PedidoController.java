package com.example.auth.controllers;

import com.example.auth.domain.pedido.Pedido;
import com.example.auth.domain.pedido.PedidoRequestDTO;
import com.example.auth.domain.pedido.PedidoResponseDTO;
import com.example.auth.domain.user.Cliente;
import com.example.auth.domain.variante.Variante;
import com.example.auth.repositories.PedidoRepository;
import com.example.auth.repositories.ClienteRepository;
import com.example.auth.repositories.VarianteRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController()
@RequestMapping("pedido")
public class PedidoController {
  @Autowired
  PedidoRepository repository;
  @Autowired
  ClienteRepository clienteRepository;
  @Autowired
  VarianteRepository varianteRepository;

  @PostMapping
  public ResponseEntity postCompra(@RequestBody @Valid PedidoRequestDTO body) {
    Optional<Cliente> cliente = this.clienteRepository.findById(body.idCliente());
    if (cliente.isEmpty()) {
      return new ResponseEntity<>("Cliente not found", HttpStatus.BAD_REQUEST);
    }

    Optional<Variante> variante = this.varianteRepository.findById(body.idVariante());
    if (variante.isEmpty()) {
      return new ResponseEntity<>("Variante not found", HttpStatus.BAD_REQUEST);
    }

    Pedido newPedido = new Pedido(body);
    newPedido.setCliente(cliente.get());
    newPedido.setVariante(variante.get());

    this.repository.save(newPedido);
    return ResponseEntity.ok().build();
  }

  @GetMapping("all")
  public ResponseEntity<List<PedidoResponseDTO>> getAllCompras() {
    List<PedidoResponseDTO> compraList = this.repository.findAll().stream().map(PedidoResponseDTO::new).toList();
    return ResponseEntity.ok(compraList);
  }
  @GetMapping
  public ResponseEntity<PedidoResponseDTO> getVariante(@RequestParam Integer id) {
    Optional<Pedido> compra = repository.findById(id);
    if (compra.isPresent()) {
      return ResponseEntity.ok(new PedidoResponseDTO(compra.get()));
    }
    return null;
  }

  @GetMapping("cliente")
  public ResponseEntity<List<PedidoResponseDTO>> getPedidosCliente(@RequestParam String id) {
    List<PedidoResponseDTO> pedidos = repository.findByCliente(id).stream().map(PedidoResponseDTO::new).toList();
    return ResponseEntity.ok(pedidos);
  }
}

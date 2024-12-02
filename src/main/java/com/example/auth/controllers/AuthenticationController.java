package com.example.auth.controllers;

import com.example.auth.domain.user.AuthDTO;
import com.example.auth.domain.user.LoginResponseDTO;
import com.example.auth.domain.user.CadastroDTO;
import com.example.auth.domain.user.Cliente;
import com.example.auth.infra.security.TokenService;
import com.example.auth.repositories.ClienteRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private ClienteRepository repository;
    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthDTO data){
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((Cliente) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid CadastroDTO data){
        if(this.repository.findByLogin(data.login()) != null) return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        Cliente newCliente = new Cliente(data, encryptedPassword);

        this.repository.save(newCliente);

        return ResponseEntity.ok().build();
    }
    @GetMapping("/users")
    ResponseEntity<List<Cliente>> get() {
      List<Cliente> clienteList = this.repository.findAll().stream().toList();
      return ResponseEntity.ok(clienteList);
    }
}

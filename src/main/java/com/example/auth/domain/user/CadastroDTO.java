package com.example.auth.domain.user;

public record CadastroDTO(String nome, String documento, String telefone, String endereco, String cep, String cidade, String estado, String complemento, String login, String password, ClienteRole role) {
}

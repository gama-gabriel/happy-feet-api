package com.example.auth.domain.produto;

public enum GeneroProduto {
  Masculino("Masculino"),
  Feminino("Feminino"),
  Unissex("Unissex"),
  Infantil("Infantil");

  private String role;

  GeneroProduto(String role) {
    this.role = role;
  }

  public String getRole() {
    return role;
  }
}

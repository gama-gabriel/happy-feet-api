package com.example.auth.domain.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Table(name = "cliente")
@Entity(name = "cliente")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Cliente implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String nome;
    private String documento;
    private String telefone;
    private String endereco;
    private String cep;
    private String cidade;
    private String estado;
    private String complemento;
    private String login;
    private String password;
    private ClienteRole role;

    public Cliente(CadastroDTO data, String encryptedPassword){
        this.nome = data.nome();
        this.documento = data.documento();
        this.telefone = data.telefone();
        this.endereco = data.endereco();
        this.cep = data.cep();
        this.cidade = data.cidade();
        this.estado = data.estado();
        this.complemento = data.complemento();
        this.login = data.login();
        this.password = encryptedPassword;
        this.role = data.role();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(this.role == ClienteRole.ADMIN) return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
        else return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

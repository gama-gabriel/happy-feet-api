package com.example.auth.infra.security;

import com.example.auth.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.context.RequestAttributeSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;


@Configuration
@EnableWebSecurity
public class SecurityConfigurations {

  @Autowired
  TokenService tokenService;

  @Autowired
  ClienteRepository clienteRepository;

  @Bean
  public SecurityContextRepository securityContextRepository() {
    return new RequestAttributeSecurityContextRepository();
  }

  @Bean
  public SecurityFilter securityFilter() {
    return new SecurityFilter(tokenService, clienteRepository, securityContextRepository());
  }

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
    return httpSecurity
      .csrf(csrf -> csrf.disable())
      .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
      .authorizeRequests(authorize -> authorize
        .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
        .requestMatchers(HttpMethod.POST, "/auth/register").permitAll()
        .requestMatchers(HttpMethod.GET, "/produto/**").permitAll()
        .requestMatchers(HttpMethod.GET, "/produto/all").permitAll()
        .requestMatchers(HttpMethod.GET, "/produto/busca").permitAll()
        .requestMatchers(HttpMethod.GET, "/variante").permitAll()
        .requestMatchers(HttpMethod.POST, "/produto").hasRole("ADMIN")
        .anyRequest().authenticated()
      )
      .addFilterBefore(securityFilter(), UsernamePasswordAuthenticationFilter.class)
      .build();
  }

  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
    return authenticationConfiguration.getAuthenticationManager();
  }

  @Autowired
  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration configuration = new CorsConfiguration();
    configuration.setAllowedOrigins(Arrays.asList("https://happy-feet-rouge.vercel.app"f);  // Frontend URL
    configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
    configuration.setAllowedHeaders(Arrays.asList("*"));  // Allow all headers
    configuration.setAllowCredentials(true);  // Allow credentials (cookies, auth tokens)

    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", configuration);  // Apply this config to all paths

    return source;
  }
}

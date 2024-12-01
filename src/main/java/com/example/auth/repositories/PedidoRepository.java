package com.example.auth.repositories;

import com.example.auth.domain.pedido.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
  @Query(value="select * from pedido where id_cliente=:termo", nativeQuery = true)
  List<Pedido> findByCliente(@Param("termo") String termo);
}

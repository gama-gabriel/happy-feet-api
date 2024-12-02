package com.example.auth.repositories;

import com.example.auth.domain.produto.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
  @Query(value="select * from produto where keywords like %:termo%", nativeQuery = true)
  List<Produto> buscar(@Param("termo") String termo);
}

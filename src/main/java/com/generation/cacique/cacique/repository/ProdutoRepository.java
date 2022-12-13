package com.generation.cacique.cacique.repository;

import com.generation.cacique.cacique.model.Categoria;
import com.generation.cacique.cacique.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto,Long> {


    public List<Produto> findAllByNomeContainingIgnoreCase(@Param("nome") String nome);
    public List<Produto> findAllByPrecoLessThan(@Param("preco") BigDecimal preco);
    public List<Produto> findAllByPrecoGreaterThan(@Param("preco")BigDecimal preco);
}
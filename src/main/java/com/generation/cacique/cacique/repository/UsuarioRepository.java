package com.generation.cacique.cacique.repository;
import com.generation.cacique.cacique.model.Produto;
import com.generation.cacique.cacique.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
    public Optional<Usuario> findByUsuario(String usuario);

}
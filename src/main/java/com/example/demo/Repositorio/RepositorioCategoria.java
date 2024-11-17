package com.example.demo.Repositorio;

import com.example.demo.Entidades.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RepositorioCategoria extends JpaRepository<Categoria, Long> {
    Optional<Categoria> findByNombreCategoria(String nombreCategoria);
}

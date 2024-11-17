package com.example.demo.Repositorio;

import com.example.demo.Entidades.Cliente;
import com.example.demo.Entidades.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RepositorioProducto extends JpaRepository<Producto, Long> {
    Optional<Producto> findByNombreProducto(String nombreProducto);
}


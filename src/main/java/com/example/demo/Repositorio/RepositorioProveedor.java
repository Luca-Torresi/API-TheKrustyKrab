package com.example.demo.Repositorio;

import com.example.demo.Entidades.Proveedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioProveedor extends JpaRepository<Proveedor, Long> {
}

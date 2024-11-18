package com.example.demo.Repositorio;

import com.example.demo.Entidades.Administrador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RepositorioAdministrador extends JpaRepository<Administrador, Long> {
    Optional<Administrador> findByEmail(String email);
}

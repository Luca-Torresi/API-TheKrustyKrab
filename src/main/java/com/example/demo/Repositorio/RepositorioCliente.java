package com.example.demo.Repositorio;

import com.example.demo.Entidades.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface RepositorioCliente extends JpaRepository<Cliente, Long> {
    Optional<Cliente> findByEmail(String email);
}

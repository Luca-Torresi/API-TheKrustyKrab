package com.example.demo.Repositorio;

import com.example.demo.Entidades.Cliente;
import com.example.demo.Entidades.Direccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RepositorioDireccion extends JpaRepository<Direccion, Long> {
    Direccion findByCliente(Cliente cliente);
}

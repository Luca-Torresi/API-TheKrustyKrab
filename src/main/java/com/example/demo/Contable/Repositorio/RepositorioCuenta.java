package com.example.demo.Contable.Repositorio;

import com.example.demo.Contable.Entidades.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RepositorioCuenta extends JpaRepository<Cuenta, Long> {
    Optional<Cuenta> findByNombreCuenta(String nombreCuenta);
}

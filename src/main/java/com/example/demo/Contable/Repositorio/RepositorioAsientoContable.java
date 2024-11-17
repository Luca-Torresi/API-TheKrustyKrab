package com.example.demo.Contable.Repositorio;

import com.example.demo.Contable.Entidades.AsientoContable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioAsientoContable extends JpaRepository<AsientoContable, Long> {
}

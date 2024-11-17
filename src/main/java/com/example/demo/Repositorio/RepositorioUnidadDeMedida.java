package com.example.demo.Repositorio;

import com.example.demo.Entidades.UnidadDeMedida;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioUnidadDeMedida extends JpaRepository<UnidadDeMedida, Long> {
}

package com.example.demo.Repositorio;

import com.example.demo.Entidades.MateriaPrima;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface RepositorioMateriaPrima extends JpaRepository<MateriaPrima, Long> {
    Optional<MateriaPrima> findByNombreMateriaPrima(String nombreMateriaPrima);
}


package com.example.demo.Repositorio;

import com.example.demo.Entidades.DetallePedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioDetallePedido extends JpaRepository<DetallePedido, Long> {
}

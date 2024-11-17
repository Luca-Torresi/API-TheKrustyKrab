package com.example.demo.Repositorio;

import com.example.demo.Entidades.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioPedido extends JpaRepository<Pedido, Long> {
}

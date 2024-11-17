package com.example.demo.Repositorio;

import com.example.demo.Entidades.Ingrediente;
import com.example.demo.Entidades.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RepositorioIngrediente extends JpaRepository<Ingrediente, Long> {
    List<Ingrediente> findByProducto(Producto producto);
}

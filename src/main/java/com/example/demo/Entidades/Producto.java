package com.example.demo.Entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder @AllArgsConstructor @NoArgsConstructor
@Entity @Table
public class Producto {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID_producto;
    private String nombreProducto;
    private String descripcion;
    private boolean esElaborado;
    private String imagen;

    @ManyToOne @JoinColumn(name = "ID_categoria")
    private Categoria categoria;
}

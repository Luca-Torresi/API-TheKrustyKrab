package com.example.demo.Entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder @AllArgsConstructor @NoArgsConstructor
@Entity @Table
public class Ingrediente {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID_ingrediente;
    private double cantidad;

    @ManyToOne @JoinColumn(name = "ID_producto")
    private Producto producto;

    @ManyToOne @JoinColumn(name = "ID_materiaPrima")
    private MateriaPrima materiaPrima;
}

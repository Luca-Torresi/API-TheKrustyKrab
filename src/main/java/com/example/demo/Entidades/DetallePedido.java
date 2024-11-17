package com.example.demo.Entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder @AllArgsConstructor @NoArgsConstructor
@Entity @Table
public class DetallePedido {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID_detallePedido;
    private int cantidadProducto;

    @ManyToOne @JoinColumn(name = "ID_pedido")
    private Pedido pedido;

    @ManyToOne @JoinColumn(name = "ID_producto")
    private Producto producto;
}

package com.example.demo.Entidades;

import com.example.demo.Enumeraciones.EstadoDelPedido;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data @Builder @AllArgsConstructor @NoArgsConstructor
@Entity @Table
public class Pedido {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID_pedido;
    private LocalDateTime fechaYHora;

    @Enumerated(EnumType.STRING)
    private EstadoDelPedido estado;

    @ManyToOne @JoinColumn(name = "ID_cliente")
    private Cliente cliente;
}

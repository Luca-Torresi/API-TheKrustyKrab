package com.example.demo.DTO;

import com.example.demo.Enumeraciones.EstadoDelPedido;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data @Builder @AllArgsConstructor @NoArgsConstructor
public class PedidoDTO {

    private String mail;
    private EstadoDelPedido estado;
    private List<DetallePedidoDTO> detalles;
}

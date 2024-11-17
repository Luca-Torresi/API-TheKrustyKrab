package com.example.demo.Controlador;

import com.example.demo.Contable.Servicio.ServicioContable;
import com.example.demo.DTO.PedidoDTO;
import com.example.demo.Servicios.ServicioPedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pedido")
public class ControladorPedido {

    private final ServicioPedido servicioPedido;
    private final ServicioContable servicioContable;

    @Autowired
    public ControladorPedido(ServicioPedido servicioPedido, ServicioContable servicioContable) {
        this.servicioPedido = servicioPedido;
        this.servicioContable = servicioContable;
    }

    @PostMapping("/nuevo")
    public ResponseEntity<String> generarNuevoPedido(@RequestBody PedidoDTO pedidoDTO) {
        servicioPedido.nuevoPedido(pedidoDTO);
        servicioContable.registrarPedido(pedidoDTO, "Registro de venta", "Caja", "Venta", 1.3);
        servicioContable.registrarPedido(pedidoDTO, "Disminución materia prima", "Costo de venta", "Mercadería", 1);

        return ResponseEntity.ok("El pedido fue guardado correctamente");
    }
}
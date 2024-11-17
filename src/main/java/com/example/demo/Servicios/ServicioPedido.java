package com.example.demo.Servicios;

import com.example.demo.DTO.DetallePedidoDTO;
import com.example.demo.DTO.PedidoDTO;
import com.example.demo.Entidades.*;
import com.example.demo.Repositorio.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ServicioPedido {

    private final ServicioMateriaPrima servicioMateriaPrima;
    private final ServicioProducto servicioProducto;
    private final RepositorioPedido repositorioPedido;
    private final RepositorioCliente repositorioCliente;
    private final RepositorioProducto repositorioProducto;
    private final RepositorioDetallePedido repositorioDetallePedido;

    @Autowired
    public ServicioPedido(ServicioMateriaPrima servicioMateriaPrima, ServicioProducto servicioProducto, RepositorioPedido repositorioPedido, RepositorioCliente repositorioCliente, RepositorioProducto repositorioProducto, RepositorioDetallePedido repositorioDetallePedido) {
        this.servicioMateriaPrima = servicioMateriaPrima;
        this.servicioProducto = servicioProducto;
        this.repositorioPedido = repositorioPedido;
        this.repositorioCliente = repositorioCliente;
        this.repositorioProducto = repositorioProducto;
        this.repositorioDetallePedido = repositorioDetallePedido;
    }

    @Transactional
    public void nuevoPedido(PedidoDTO pedidoDTO){
        Pedido pedido = Pedido.builder()
                .fechaYHora(LocalDateTime.now())
                .estado(pedidoDTO.getEstado())
                .build();

        Optional<Cliente> clienteOpcional = repositorioCliente.findByEmail(pedidoDTO.getMail());
        pedido.setCliente(clienteOpcional.get());

        repositorioPedido.save(pedido);

        for(DetallePedidoDTO detallePedidoDTO : pedidoDTO.getDetalles()){
            DetallePedido detallePedido = DetallePedido.builder()
                    .cantidadProducto(detallePedidoDTO.getCantidadProducto())
                    .pedido(pedido)
                    .build();

            Optional<Producto> productoOpcional = repositorioProducto.findByNombreProducto(detallePedidoDTO.getNombreProducto());
            detallePedido.setProducto(productoOpcional.get());

            repositorioDetallePedido.save(detallePedido);
        }
        servicioMateriaPrima.descontarMateriaPrima(pedidoDTO);
    }

    public double calcularTotal(PedidoDTO pedidoDTO){
        double total = 0;

        for(DetallePedidoDTO detallePedidoDTO : pedidoDTO.getDetalles()){
            Optional<Producto> productoOpcional = repositorioProducto.findByNombreProducto(detallePedidoDTO.getNombreProducto());

             double costoProducto = servicioProducto.calcularCostoProducto(productoOpcional.get());

             total += detallePedidoDTO.getCantidadProducto() * costoProducto;
        }
        return total;
    }
}

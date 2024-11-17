package com.example.demo.Servicios;

import com.example.demo.DTO.*;
import com.example.demo.Entidades.ActualizacionCosto;
import com.example.demo.Entidades.Ingrediente;
import com.example.demo.Entidades.MateriaPrima;
import com.example.demo.Entidades.Producto;
import com.example.demo.Repositorio.RepositorioActualizacionCosto;
import com.example.demo.Repositorio.RepositorioIngrediente;
import com.example.demo.Repositorio.RepositorioMateriaPrima;
import com.example.demo.Repositorio.RepositorioProducto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ServicioMateriaPrima {

    private final RepositorioMateriaPrima repositorioMateriaPrima;
    private final RepositorioProducto repositorioProducto;
    private final RepositorioIngrediente repositorioIngrediente;
    private final RepositorioActualizacionCosto repositorioActualizacionCosto;

    @Autowired
    public ServicioMateriaPrima(RepositorioMateriaPrima repositorioMateriaPrima, RepositorioProducto repositorioProducto, RepositorioIngrediente repositorioIngrediente, RepositorioActualizacionCosto repositorioActualizacionCosto) {
        this.repositorioMateriaPrima = repositorioMateriaPrima;
        this.repositorioProducto = repositorioProducto;
        this.repositorioIngrediente = repositorioIngrediente;
        this.repositorioActualizacionCosto = repositorioActualizacionCosto;
    }

    public void descontarMateriaPrima(PedidoDTO pedidoDTO){
        for(DetallePedidoDTO detallePedidoDTO : pedidoDTO.getDetalles()){
            Optional<Producto> productoOpcional = repositorioProducto.findByNombreProducto(detallePedidoDTO.getNombreProducto());

            List<Ingrediente> ingredientes = repositorioIngrediente.findByProducto(productoOpcional.get());

            for(Ingrediente ingrediente: ingredientes){
                Optional<MateriaPrima> materiaPrimaOpcional = repositorioMateriaPrima.findById(ingrediente.getMateriaPrima().getID_materiaPrima());

                if (materiaPrimaOpcional.isPresent()) {
                    MateriaPrima materiaPrima = materiaPrimaOpcional.get();

                    materiaPrima.setStockActual(materiaPrima.getStockActual() - (detallePedidoDTO.getCantidadProducto() * ingrediente.getCantidad()));
                    repositorioMateriaPrima.save(materiaPrima);
                }
            }
        }
    }

    public double cargarMateriaPrima(CargaMateriaPrimaDTO cargaMateriaPrimaDTO){
        double total = 0;

        for(MateriaPrimaDTO materiaPrimaDTO : cargaMateriaPrimaDTO.getCarga()){
            Optional<MateriaPrima> materiaPrimaOpcional = repositorioMateriaPrima.findByNombreMateriaPrima(materiaPrimaDTO.getNombreMateriaPrima());
            MateriaPrima materiaPrima = materiaPrimaOpcional.get();

            materiaPrima.setStockActual(materiaPrima.getStockActual() + materiaPrimaDTO.getCantidad());
            repositorioMateriaPrima.save(materiaPrima);

            ActualizacionCosto actualizacionCosto = repositorioActualizacionCosto.findUltimaActualizacion(materiaPrima.getID_materiaPrima());
            total += materiaPrimaDTO.getCantidad() * actualizacionCosto.getCosto();
        }
        return total;
    }

    public void modificarCostoMateriaPrima(ActualizacionCostoDTO actualizacionCostoDTO){
        ActualizacionCosto actualizacionCosto = ActualizacionCosto.builder()
                .costo(actualizacionCostoDTO.getNuevoCosto())
                .fechaYHora(LocalDateTime.now())
                .build();

        Optional<MateriaPrima> materiaPrimaOpcional = repositorioMateriaPrima.findByNombreMateriaPrima(actualizacionCostoDTO.getNombreMateriaPrima());
        actualizacionCosto.setMateriaPrima(materiaPrimaOpcional.get());

        repositorioActualizacionCosto.save(actualizacionCosto);
    }

    public ArregloMateriaPrimaDTO llenarArregloMateriaPrima(){
        ArregloMateriaPrimaDTO arregloMateriaPrimaDTO = new ArregloMateriaPrimaDTO();

        for(MateriaPrima materiaPrima : repositorioMateriaPrima.findAll()){
            NombreMateriaPrimaDTO nombreMateriaPrimaDTO = NombreMateriaPrimaDTO.builder()
                    .nombreMateriaPrima(materiaPrima.getNombreMateriaPrima())
                    .build();

            arregloMateriaPrimaDTO.getArregloNombres().add(nombreMateriaPrimaDTO);
        }
        return arregloMateriaPrimaDTO;
    }
}

package com.example.demo.Servicios;

import com.example.demo.DTO.IngredienteDTO;
import com.example.demo.DTO.NuevoProductoDTO;
import com.example.demo.DTO.PrecioUnitarioDTO;
import com.example.demo.DTO.PreciosDTO;
import com.example.demo.Entidades.*;
import com.example.demo.Repositorio.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServicioProducto {

    private final RepositorioProducto repositorioProducto;
    private final RepositorioIngrediente repositorioIngrediente;
    private final RepositorioCategoria repositorioCategoria;
    private final RepositorioMateriaPrima repositorioMateriaPrima;
    private final RepositorioActualizacionCosto repositorioActualizacionCosto;

    @Autowired
    public ServicioProducto(RepositorioProducto repositorioProducto, RepositorioIngrediente repositorioIngrediente, RepositorioCategoria repositorioCategoria, RepositorioMateriaPrima repositorioMateriaPrima, RepositorioActualizacionCosto repositorioActualizacionCosto) {
        this.repositorioProducto = repositorioProducto;
        this.repositorioIngrediente = repositorioIngrediente;
        this.repositorioCategoria = repositorioCategoria;
        this.repositorioMateriaPrima = repositorioMateriaPrima;
        this.repositorioActualizacionCosto = repositorioActualizacionCosto;
    }

    @Transactional
    public void guardar(NuevoProductoDTO nuevoProductoDTO) {
        Producto producto = Producto.builder()
                .nombreProducto(nuevoProductoDTO.getNombreProducto())
                .descripcion(nuevoProductoDTO.getDescripcion())
                .esElaborado(nuevoProductoDTO.isEsElaborado())
                .imagen(nuevoProductoDTO.getImagen())
                .build();

        Optional<Categoria> categoriaOpcional = repositorioCategoria.findByNombreCategoria(nuevoProductoDTO.getNombreCategoria());
        producto.setCategoria(categoriaOpcional.get());

        repositorioProducto.save(producto);

        for(IngredienteDTO ingredienteDTO : nuevoProductoDTO.getIngredientes()) {
            Ingrediente ingrediente = Ingrediente.builder()
                    .producto(producto)
                    .cantidad(ingredienteDTO.getCantidad())
                    .build();

            Optional<MateriaPrima> materiaPrimaOpcional = repositorioMateriaPrima.findByNombreMateriaPrima(ingredienteDTO.getNombreMateriaPrima());
            ingrediente.setMateriaPrima(materiaPrimaOpcional.get());

            repositorioIngrediente.save(ingrediente);
        }
    }

    public PreciosDTO actualizarPrecioProducto(){
        List<Producto> productos = repositorioProducto.findAll();

        PreciosDTO preciosDTO = new PreciosDTO();

        for(Producto producto : productos){
            PrecioUnitarioDTO precioUnitarioDTO = PrecioUnitarioDTO.builder()
                    .nombreProducto(producto.getNombreProducto())
                    .descripcion(producto.getDescripcion())
                    .imagen(producto.getImagen())
                    .precio(Math.round(calcularCostoProducto(producto) * 1.3))
                    .build();

            preciosDTO.getPrecios().add(precioUnitarioDTO);
        }
        return preciosDTO;
    }

    public double calcularCostoProducto(Producto producto){
        List<Ingrediente> ingredientes = repositorioIngrediente.findByProducto(producto);

        double total = 0;
        for(Ingrediente ingrediente : ingredientes){
            Optional<MateriaPrima> materiaPrimaOpcional = repositorioMateriaPrima.findById(ingrediente.getMateriaPrima().getID_materiaPrima());
            MateriaPrima materiaPrima = materiaPrimaOpcional.get();

            ActualizacionCosto actualizacionCosto = repositorioActualizacionCosto.findUltimaActualizacion(materiaPrima.getID_materiaPrima());

            total += ingrediente.getCantidad() * actualizacionCosto.getCosto();
        }
        System.out.println(total);
        return total;
    }
}

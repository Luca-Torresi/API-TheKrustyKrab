package com.example.demo.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder @AllArgsConstructor @NoArgsConstructor
public class PrecioUnitarioDTO {

    private String nombreProducto;
    private String descripcion;
    private String imagen;
    private double precio;
}

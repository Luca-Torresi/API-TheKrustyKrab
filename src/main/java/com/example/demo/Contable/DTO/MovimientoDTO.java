package com.example.demo.Contable.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder @AllArgsConstructor @NoArgsConstructor
public class MovimientoDTO {

    private String nombreCuenta;
    private String tipo;
    private double monto;
}

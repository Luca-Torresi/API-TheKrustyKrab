package com.example.demo.Contable.DTO;

import com.example.demo.Contable.Enumeraciones.TipoDeCuenta;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder @AllArgsConstructor @NoArgsConstructor
public class CuentaDTO {

    private String nombreCuenta;
    private TipoDeCuenta tipoDeCuenta;
}

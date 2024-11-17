package com.example.demo.Contable.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data @Builder @AllArgsConstructor @NoArgsConstructor
public class FechasDTO {

    private String nombreCuenta;
    private LocalDate fechaInicio;
    private LocalDate fechaCierre;
}

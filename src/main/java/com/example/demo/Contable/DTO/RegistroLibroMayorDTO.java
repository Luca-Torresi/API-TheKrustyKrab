package com.example.demo.Contable.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data @Builder @AllArgsConstructor @NoArgsConstructor
public class RegistroLibroMayorDTO {

    private LocalDate fecha;
    private String nombre_cuenta;
    private double debe;
    private double haber;
    private double saldo;
}

package com.example.demo.Contable.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data @Builder @AllArgsConstructor @NoArgsConstructor
public class RegistroLibroDiarioDTO {

    private Long id_asiento_contable;
    private Date fecha;
    private String descripcion;
    private String nombre_cuenta;
    private double debe;
    private double haber;
}
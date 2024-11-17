package com.example.demo.Contable.Entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data @Builder @AllArgsConstructor @NoArgsConstructor
@Entity @Table
public class AsientoContable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID_asientoContable;
    private LocalDate fecha;
    private String descripcion;
}



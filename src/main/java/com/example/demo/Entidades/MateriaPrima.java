package com.example.demo.Entidades;

import lombok.*;
import jakarta.persistence.*;

@Data @Builder @AllArgsConstructor @NoArgsConstructor
@Entity @Table
public class MateriaPrima {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID_materiaPrima;
    private String nombreMateriaPrima;
    private double stockMinimo;
    private double stockMaximo;
    @Column(columnDefinition = "double default 0.0")
    private double stockActual;

    @ManyToOne @JoinColumn(name = "ID_proveedor")
    private Proveedor proveedor;

    @ManyToOne @JoinColumn(name = "ID_unidadDeMedida")
    private UnidadDeMedida unidadDeMedida;
}

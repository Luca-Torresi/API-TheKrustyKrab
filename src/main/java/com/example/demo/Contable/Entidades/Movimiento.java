package com.example.demo.Contable.Entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder @AllArgsConstructor @NoArgsConstructor
@Entity @Table
public class Movimiento {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID_movimiento;
    private double monto;
    private String tipo;

    @ManyToOne @JoinColumn(name = "ID_asientoContable")
    private AsientoContable asientoContable;

    @ManyToOne @JoinColumn(name = "ID_cuenta")
    private Cuenta cuenta;
}

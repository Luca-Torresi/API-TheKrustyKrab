package com.example.demo.Contable.Entidades;

import com.example.demo.Contable.Enumeraciones.TipoDeCuenta;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder @AllArgsConstructor @NoArgsConstructor
@Entity @Table
public class Cuenta {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID_cuenta;
    private String nombreCuenta;

    @Enumerated(EnumType.STRING)
    private TipoDeCuenta tipoDeCuenta;
}

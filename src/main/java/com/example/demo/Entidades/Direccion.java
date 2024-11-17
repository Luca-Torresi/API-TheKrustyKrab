package com.example.demo.Entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder @AllArgsConstructor @NoArgsConstructor
@Entity @Table
public class Direccion {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID_direccion;
    private int codigoPostal;
    private String calle;
    private String numero;
    private String piso;
    private String departamento;
    private String referenciasAdicionales;

    @ManyToOne @JoinColumn(name = "ID_cliente")
    private Cliente cliente;
}

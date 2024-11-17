package com.example.demo.Entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder @AllArgsConstructor @NoArgsConstructor
@Entity @Table
public class Proveedor {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID_proveedor;
    private String nombreProveedor;
    private String contacto;
}

package com.example.demo.Entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder @AllArgsConstructor @NoArgsConstructor
@Entity @Table
public class Administrador {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID_administrador;
    private String nombre;
    private String apellido;
    private String email;
    private String telefono;
    private String password;
}

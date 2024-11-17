package com.example.demo.Entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder @AllArgsConstructor @NoArgsConstructor
@Entity @Table
public class Categoria {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID_categoria;
    private String nombreCategoria;
}

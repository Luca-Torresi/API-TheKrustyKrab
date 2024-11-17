package com.example.demo.Entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data @Builder @AllArgsConstructor @NoArgsConstructor
@Entity @Table
public class ActualizacionCosto {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID_actualizacionCosto;
    private LocalDateTime fechaYHora;
    private double costo;

    @ManyToOne @JoinColumn(name = "ID_materiaPrima")
    private MateriaPrima materiaPrima;

}

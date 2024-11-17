package com.example.demo.Respuestas;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder @AllArgsConstructor @NoArgsConstructor
public class RespuestaDatosClienteDTO {

    private String nombre;
    private String apellido;
    private String email;
    private String telefono;
}

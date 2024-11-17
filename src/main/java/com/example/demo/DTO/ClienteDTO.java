package com.example.demo.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder @AllArgsConstructor @NoArgsConstructor
public class ClienteDTO {

    private String nombre;
    private String apellido;
    private String email;
    private String telefono;
    private String password;
    private String imagen;
}

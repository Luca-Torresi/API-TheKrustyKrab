package com.example.demo.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder @AllArgsConstructor @NoArgsConstructor
public class EditarPerfilDTO {

    private String email;
    private String telefono;
    private String imagen;
    private String password;
}

package com.example.demo.Respuestas;

import com.example.demo.DTO.DireccionDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data @Builder @AllArgsConstructor @NoArgsConstructor
public class RespuestaDireccionesDTO {

    private List<DireccionDTO> direcciones;
}

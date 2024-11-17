package com.example.demo.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;

@Data @Builder @AllArgsConstructor @NoArgsConstructor
public class ArregloMateriaPrimaDTO {

    private List<NombreMateriaPrimaDTO> arregloNombres = new ArrayList<NombreMateriaPrimaDTO>();
}

package com.example.demo.Contable.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data @Builder @AllArgsConstructor @NoArgsConstructor
public class ArregloCuentasDTO {

    private List<CuentaDTO> cuentas = new ArrayList<>();
}

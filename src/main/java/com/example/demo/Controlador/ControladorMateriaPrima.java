package com.example.demo.Controlador;

import com.example.demo.Contable.Servicio.ServicioContable;
import com.example.demo.DTO.ActualizacionCostoDTO;
import com.example.demo.DTO.ArregloMateriaPrimaDTO;
import com.example.demo.DTO.CargaMateriaPrimaDTO;
import com.example.demo.Servicios.ServicioMateriaPrima;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/materiaPrima")
public class ControladorMateriaPrima {

    private final ServicioMateriaPrima servicioMateriaPrima;
    private final ServicioContable servicioContable;

    @Autowired
    public ControladorMateriaPrima(ServicioMateriaPrima servicioMateriaPrima, ServicioContable servicioContable) {
        this.servicioMateriaPrima = servicioMateriaPrima;
        this.servicioContable = servicioContable;
    }

    @PostMapping("/modificarCosto")
    public ResponseEntity<String> modificarCostoMateriaPrima(@RequestBody ActualizacionCostoDTO ActualizacionCostoDTO) {
        servicioMateriaPrima.modificarCostoMateriaPrima(ActualizacionCostoDTO);

        return ResponseEntity.ok("El nuevo costo fue guardado correctamente");
    }

    @PostMapping("/recargarStock")
    public void recargarStock(@RequestBody CargaMateriaPrimaDTO cargaMateriaPrimaDTO) {
        double total = servicioMateriaPrima.cargarMateriaPrima(cargaMateriaPrimaDTO);

        servicioContable.registrarCompraProveedor(total);
    }

    @GetMapping("/obtenerNombres")
    public ResponseEntity<ArregloMateriaPrimaDTO> obtenerNombresMateriaPrima() {
        return ResponseEntity.ok(servicioMateriaPrima.llenarArregloMateriaPrima());
    }
}
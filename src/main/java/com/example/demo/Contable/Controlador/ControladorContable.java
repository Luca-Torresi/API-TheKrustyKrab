package com.example.demo.Contable.Controlador;

import com.example.demo.Contable.DTO.*;
import com.example.demo.Contable.Repositorio.RepositorioMovimiento;
import com.example.demo.Contable.Servicio.ServicioContable;
import com.example.demo.Excepciones.ExcepcionAsientoDesbalanceado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

@RestController
@RequestMapping("/administrador")
public class ControladorContable {

    private final ServicioContable servicioContable;
    private final RepositorioMovimiento repositorioMovimiento;

    @Autowired
    public ControladorContable(ServicioContable servicioContable, RepositorioMovimiento repositorioMovimiento) {
        this.servicioContable = servicioContable;
        this.repositorioMovimiento = repositorioMovimiento;
    }

    @PostMapping("/nuevoAsientoContable")
    public ResponseEntity<String> nuevoAsientoContable(@RequestBody AsientoContableDTO asientoContableDTO){
        try{
            servicioContable.cargarAsientoContable(asientoContableDTO);
            return ResponseEntity.ok("El asiento contable se guardó de manera exitosa");
        } catch(ExcepcionAsientoDesbalanceado ex){
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @PostMapping("/nuevaCuenta")
    public void crearNuevaCuenta(@RequestBody CuentaDTO cuentaDTO){
        servicioContable.crearCuenta(cuentaDTO);
    }

    @GetMapping("/libroDiario")
    public ResponseEntity<LibroDiarioDTO> mostrarLibroDiario(){
        List<Object[]> resultados = repositorioMovimiento.obtenerLibroDiario();

        List<RegistroLibroDiarioDTO> registros = new ArrayList<>();
        for (Object[] resultado : resultados) {
            RegistroLibroDiarioDTO registroLibroDiarioDTO = RegistroLibroDiarioDTO.builder()
                    .id_asiento_contable((Long) resultado[0])
                    .fecha((Date) resultado[1])
                    .descripcion((String) resultado[2])
                    .nombre_cuenta((String) resultado[3])
                    .debe(Math.round((Double) resultado[4]))
                    .haber(Math.round((Double) resultado[5]))
                    .build();

            registros.add(registroLibroDiarioDTO);
        }

        LibroDiarioDTO libroDiarioDTO = LibroDiarioDTO.builder()
                .registros(registros)
                .build();

        return ResponseEntity.ok(libroDiarioDTO);
    }

    @PostMapping("/libroMayor")
    public ResponseEntity<LibroMayorDTO> mostrarLibroMayor(@RequestBody FechasDTO fechasDTO) {
        List<Object[]> resultados = repositorioMovimiento.obtenerLibroMayor(fechasDTO.getNombreCuenta(),fechasDTO.getFechaInicio(),fechasDTO.getFechaCierre());

        List<RegistroLibroMayorDTO> registros = new ArrayList<>();
        for (Object[] resultado : resultados) {
            RegistroLibroMayorDTO registroLibroMayorDTO = RegistroLibroMayorDTO.builder()
                    .fecha(resultado[0] != null ? ((java.sql.Date) resultado[0]).toLocalDate() : null)
                    .nombre_cuenta((String) resultado[1])
                    .debe(resultado[2] != null ? Math.round((Double) resultado[2]) : 0)
                    .haber(resultado[3] != null ? Math.round((Double) resultado[3]) : 0)
                    .saldo(resultado[4] != null ? Math.round((Double) resultado[4]) : 0)
                    .build();
            registros.add(registroLibroMayorDTO);
        }

        LibroMayorDTO libroMayorDTO = LibroMayorDTO.builder()
                .registros(registros)
                .build();

        return ResponseEntity.ok(libroMayorDTO);
    }

    @GetMapping("/obtenerCuentas")
    public ResponseEntity<ArregloCuentasDTO> obtenerCuentas(){
        return ResponseEntity.ok(servicioContable.obtenerArregloCuentas());
    }
}

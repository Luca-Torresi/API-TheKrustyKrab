package com.example.demo.Controlador;

import com.example.demo.DTO.*;
import com.example.demo.Repositorio.RepositorioCliente;
import com.example.demo.Respuestas.RespuestaDatosClienteDTO;
import com.example.demo.Respuestas.RespuestaDireccionesDTO;
import com.example.demo.Respuestas.RespuestaInicioDeSesion;
import com.example.demo.Servicios.ServicioCliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cliente")
public class ControladorCliente {

    private final ServicioCliente servicioCliente;
    private final RepositorioCliente repositorioCliente;

    @Autowired
    public ControladorCliente(ServicioCliente servicioCliente, RepositorioCliente repositorioCliente) {
        this.servicioCliente = servicioCliente;
        this.repositorioCliente = repositorioCliente;
    }

    @PostMapping("/registrar")
    public ResponseEntity<String> registrar(@RequestBody ClienteDTO clienteDTO) {
        try {
            servicioCliente.guardarCliente(clienteDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body("Cliente registrado exitosamente.");
        } catch (DataIntegrityViolationException ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("El email ya existe.");
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocurrió un error inesperado.");
        }
    }

    @PostMapping("/inicioDeSesion")
    public ResponseEntity<RespuestaInicioDeSesion> inicioDeSesion(@RequestBody InicioDeSesionDTO inicioDeSesionDTO) {
        RespuestaInicioDeSesion respuestaInicioDeSesion = new RespuestaInicioDeSesion();

        if(servicioCliente.verificarInicioDeSesion(respuestaInicioDeSesion, inicioDeSesionDTO)){
            return ResponseEntity.ok(respuestaInicioDeSesion);
        } else{
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(respuestaInicioDeSesion);
        }
    }

    @PostMapping("/guardarDireccion")
    public ResponseEntity<String> guardarDireccion(@RequestBody DireccionDTO direccionDTO) {
        servicioCliente.guardarNuevaDireccion(direccionDTO);

        return ResponseEntity.ok("La dirección fue guardada correctamente");
    }

    @PostMapping("/obtenerDirecciones")
    public ResponseEntity<RespuestaDireccionesDTO> obtenerDirecciones(@RequestBody EmailDTO emailDTO) {
        return ResponseEntity.ok(servicioCliente.obtenerListaDirecciones(emailDTO));
    }

    @PostMapping("/obtenerDatos")
    public ResponseEntity<RespuestaDatosClienteDTO> obtenerDatosCliente(@RequestBody EmailDTO emailDTO) {
        return ResponseEntity.ok(servicioCliente.obtenerDatosCliente(emailDTO));
    }

    @PostMapping("/editarPerfil")
    public void editarPerfil(@RequestBody EditarPerfilDTO editarPerfilDTO) {
        servicioCliente.modificarDatosCliente(editarPerfilDTO);
    }

    @PostMapping("/eliminarDireccion")
    public void eliminarDireccion(@RequestBody EliminarDireccionDTO eliminarDireccionDTO) {
        servicioCliente.borrarDireccion(eliminarDireccionDTO);
    }
}


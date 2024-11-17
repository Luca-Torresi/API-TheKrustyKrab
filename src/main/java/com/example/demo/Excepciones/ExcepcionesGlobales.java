package com.example.demo.Excepciones;

import com.example.demo.Respuestas.RespuestaError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExcepcionesGlobales {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<RespuestaError> manejoExcepcionesGenerales(Exception ex) {
        RespuestaError respuestaError = new RespuestaError(ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(respuestaError);
    }

    @ExceptionHandler(ExcepcionPersonalizada.class)
    public ResponseEntity<RespuestaError> manejoExcepcionesPersonalizadas(ExcepcionPersonalizada ex) {
        RespuestaError errorResponse = new RespuestaError(ex.getMessage());
        return ResponseEntity.badRequest().body(errorResponse);
    }
}
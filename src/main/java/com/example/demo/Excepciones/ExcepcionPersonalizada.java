package com.example.demo.Excepciones;

public class ExcepcionPersonalizada extends RuntimeException {
    public ExcepcionPersonalizada(String mensaje) {
      super(mensaje);
    }
}

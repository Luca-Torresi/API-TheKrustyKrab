package com.example.demo.Contable.Servicio;

import com.example.demo.Contable.DTO.ArregloCuentasDTO;
import com.example.demo.Contable.DTO.AsientoContableDTO;
import com.example.demo.Contable.DTO.CuentaDTO;
import com.example.demo.Contable.DTO.MovimientoDTO;
import com.example.demo.Contable.Entidades.AsientoContable;
import com.example.demo.Contable.Entidades.Cuenta;
import com.example.demo.Contable.Entidades.Movimiento;
import com.example.demo.Contable.Repositorio.RepositorioAsientoContable;
import com.example.demo.Contable.Repositorio.RepositorioCuenta;
import com.example.demo.Contable.Repositorio.RepositorioMovimiento;
import com.example.demo.DTO.PedidoDTO;
import com.example.demo.Excepciones.ExcepcionAsientoDesbalanceado;
import com.example.demo.Servicios.ServicioPedido;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ServicioContable {

    private final RepositorioAsientoContable repositorioAsientoContable;
    private final RepositorioCuenta repositorioCuenta;
    private final RepositorioMovimiento repositorioMovimiento;
    private final ServicioPedido servicioPedido;

    public ServicioContable(RepositorioAsientoContable repositorioAsientoContable, RepositorioCuenta repositorioCuenta, RepositorioMovimiento repositorioMovimiento, ServicioPedido servicioPedido) {
        this.repositorioAsientoContable = repositorioAsientoContable;
        this.repositorioCuenta = repositorioCuenta;
        this.repositorioMovimiento = repositorioMovimiento;
        this.servicioPedido = servicioPedido;
    }

    @Transactional
    public void cargarAsientoContable(AsientoContableDTO asientoContableDTO){
        if(! esBalanceado(asientoContableDTO.getMovimientos())){
            throw new ExcepcionAsientoDesbalanceado("El asiento que se intenta guardar en la base de datos no está balanceado correctamente");
        }

        AsientoContable asientoContable = AsientoContable.builder()
                .descripcion(asientoContableDTO.getDescripcion())
                .fecha(LocalDate.now())
                .build();

        repositorioAsientoContable.save(asientoContable);

        for(MovimientoDTO movimientoDTO : asientoContableDTO.getMovimientos()){
            Movimiento movimiento = Movimiento.builder()
                    .tipo(movimientoDTO.getTipo())
                    .monto(movimientoDTO.getMonto())
                    .build();

            Optional<Cuenta> cuentaOpcional = repositorioCuenta.findByNombreCuenta(movimientoDTO.getNombreCuenta());
            movimiento.setCuenta(cuentaOpcional.get());

            repositorioMovimiento.save(movimiento);
        }
    }

    public boolean esBalanceado(List<MovimientoDTO> movimientos){
        double totalDebe = 0, totalHaber = 0;

        for(MovimientoDTO movimientoDTO : movimientos){
            switch(movimientoDTO.getTipo()){
                case "Debe":
                    totalDebe += movimientoDTO.getMonto();
                    break;
                case "Haber":
                    totalHaber += movimientoDTO.getMonto();
            }
        }
        return totalDebe == totalHaber;
    }

    @Transactional
    public void registrarPedido(PedidoDTO pedidoDTO, String descripcion, String cuentaDebe, String cuentaHaber, double valor){
        AsientoContable asientoContable = AsientoContable.builder()
                .fecha(LocalDate.now())
                .descripcion(descripcion)
                .build();

        repositorioAsientoContable.save(asientoContable);

        Optional<Cuenta> cuentaDebeOpcional = repositorioCuenta.findByNombreCuenta(cuentaDebe);
        Movimiento movimientoDebe = Movimiento.builder()
                .asientoContable(asientoContable)
                .cuenta(cuentaDebeOpcional.get())
                .tipo("Debe")
                .monto(servicioPedido.calcularTotal(pedidoDTO) * valor)
                .build();

        repositorioMovimiento.save(movimientoDebe);

        Optional<Cuenta> cuentaHaberOpcional = repositorioCuenta.findByNombreCuenta(cuentaHaber);
        Movimiento movimientoHaber = Movimiento.builder()
                .asientoContable(asientoContable)
                .cuenta(cuentaHaberOpcional.get())
                .tipo("Haber")
                .monto(servicioPedido.calcularTotal(pedidoDTO) * valor)
                .build();

        repositorioMovimiento.save(movimientoHaber);
    }

    @Transactional
    public void registrarCompraProveedor(double total){
        AsientoContable asientoContable = AsientoContable.builder()
                .fecha(LocalDate.now())
                .descripcion("Compra de materia prima a proveedor")
                .build();

        repositorioAsientoContable.save(asientoContable);

        Optional<Cuenta> cuenta1 = repositorioCuenta.findByNombreCuenta("Mercadería");
        Movimiento movimiento1 = Movimiento.builder()
                .asientoContable(asientoContable)
                .cuenta(cuenta1.get())
                .tipo("Debe")
                .monto(total)
                .build();

        repositorioMovimiento.save(movimiento1);

        Optional<Cuenta> cuenta2 = repositorioCuenta.findByNombreCuenta("Proveedor");
        Movimiento movimiento2 = Movimiento.builder()
                .asientoContable(asientoContable)
                .cuenta(cuenta2.get())
                .tipo("Haber")
                .monto(total)
                .build();

        repositorioMovimiento.save(movimiento2);

        Optional<Cuenta> cuenta3 = repositorioCuenta.findByNombreCuenta("Proveedor");
        Movimiento movimiento3 = Movimiento.builder()
                .asientoContable(asientoContable)
                .cuenta(cuenta3.get())
                .tipo("Debe")
                .monto(total)
                .build();

        repositorioMovimiento.save(movimiento3);

        Optional<Cuenta> cuenta4 = repositorioCuenta.findByNombreCuenta("Caja");
        Movimiento movimiento4 = Movimiento.builder()
                .asientoContable(asientoContable)
                .cuenta(cuenta4.get())
                .tipo("Haber")
                .monto(total)
                .build();

        repositorioMovimiento.save(movimiento4);
    }

    public void crearCuenta(CuentaDTO cuentaDTO){
        Cuenta cuenta = Cuenta.builder()
                .nombreCuenta(cuentaDTO.getNombreCuenta())
                .tipoDeCuenta(cuentaDTO.getTipoDeCuenta())
                .build();

        repositorioCuenta.save(cuenta);
    }

    public ArregloCuentasDTO obtenerArregloCuentas(){
        ArregloCuentasDTO arregloCuentas = new ArregloCuentasDTO();

        for(Cuenta cuenta : repositorioCuenta.findAll()){
            CuentaDTO cuentaDTO = CuentaDTO.builder()
                    .nombreCuenta(cuenta.getNombreCuenta())
                    .tipoDeCuenta(cuenta.getTipoDeCuenta())
                    .build();

            arregloCuentas.getCuentas().add(cuentaDTO);
        }
        return arregloCuentas;
    }
}

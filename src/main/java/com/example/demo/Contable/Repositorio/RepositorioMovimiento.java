package com.example.demo.Contable.Repositorio;

import com.example.demo.Contable.Entidades.Movimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface RepositorioMovimiento extends JpaRepository<Movimiento, Long> {
    @Query(value = "SELECT \n" +
            "    CASE \n" +
            "        WHEN ROW_NUMBER() OVER (PARTITION BY asiento_contable.id_asiento_contable ORDER BY movimiento.id_movimiento) = 1 \n" +
            "        THEN asiento_contable.id_asiento_contable\n" +
            "        ELSE NULL \n" +
            "    END AS id_asiento_contable,\n" +
            "    CASE \n" +
            "        WHEN ROW_NUMBER() OVER (PARTITION BY asiento_contable.id_asiento_contable ORDER BY movimiento.id_movimiento) = 1 \n" +
            "        THEN fecha\n" +
            "        ELSE NULL \n" +
            "    END AS fecha,\n" +
            "    CASE \n" +
            "        WHEN ROW_NUMBER() OVER (PARTITION BY asiento_contable.id_asiento_contable ORDER BY movimiento.id_movimiento) = 1 \n" +
            "        THEN descripcion\n" +
            "        ELSE NULL \n" +
            "    END AS descripcion,\n" +
            "    nombre_cuenta, \n" +
            "    CASE WHEN tipo = 'debe' THEN monto ELSE 0 END AS debe, \n" +
            "    CASE WHEN tipo = 'haber' THEN monto ELSE 0 END AS haber\n" +
            "FROM movimiento\n" +
            "INNER JOIN cuenta ON movimiento.id_cuenta = cuenta.id_cuenta\n" +
            "INNER JOIN asiento_contable ON movimiento.id_asiento_contable = asiento_contable.id_asiento_contable\n" +
            "WHERE fecha = :fecha;", nativeQuery = true)
    List<Object[]> obtenerLibroDiario(@Param("fecha") LocalDate fecha);

    @Query(value = "SELECT fecha, nombre_cuenta, \n" +
            "    CASE WHEN tipo = 'debe' THEN monto ELSE 0 END AS debe,\n" +
            "    CASE WHEN tipo = 'haber' THEN monto ELSE 0 END AS haber,\n" +
            "    SUM(CASE WHEN tipo = 'debe' THEN monto ELSE -monto END) OVER (PARTITION BY nombre_cuenta ORDER BY fecha, id_movimiento) AS saldo\n" +
            "FROM movimiento \n" +
            "INNER JOIN cuenta ON movimiento.id_cuenta = cuenta.id_cuenta\n" +
            "INNER JOIN asiento_contable ON movimiento.id_asiento_contable = asiento_contable.id_asiento_contable\n" +
            "WHERE nombre_cuenta = :nombre_cuenta AND fecha BETWEEN :fecha_inicio AND :fecha_cierre\n" +
            "ORDER BY nombre_cuenta, fecha, id_movimiento;", nativeQuery = true)
    List<Object[]> obtenerLibroMayor(@Param("nombre_cuenta") String nombreCuenta, @Param("fecha_inicio") LocalDate fechaInicio, @Param("fecha_cierre") LocalDate fechaCierre);
}

package com.example.demo.Repositorio;

import com.example.demo.Entidades.ActualizacionCosto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioActualizacionCosto extends JpaRepository<ActualizacionCosto, Long>{
    @Query(value = "SELECT * FROM actualizacion_costo WHERE id_materia_prima = :ID_materiaPrima ORDER BY fechayhora DESC LIMIT 1", nativeQuery = true)
    ActualizacionCosto findUltimaActualizacion(@Param("ID_materiaPrima") Long ID_materiaPrima);
}

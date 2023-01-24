
package com.vivienda.venta.repository;

import com.vivienda.venta.domain.Provincia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface ProvinciaRepository extends JpaRepository<Provincia,String>{
    
    @Query("SELECT F FROM Provincia F WHERE F.nombre = :nombre")
    public Provincia buscarXNombre(@Param("nombre")String nombre);
}

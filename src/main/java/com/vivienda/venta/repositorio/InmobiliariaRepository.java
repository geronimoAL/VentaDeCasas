
package com.vivienda.venta.repositorio;

import com.vivienda.venta.entidades.Inmobiliaria;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;



@Repository
public interface InmobiliariaRepository extends JpaRepository<Inmobiliaria,String>{
    
    
    @Query("SELECT F FROM Inmobiliaria F")
    public List<Inmobiliaria>lista();
    
    @Query("SELECT F FROM Inmobiliaria F WHERE F.id = :id")
    public Inmobiliaria inmobiliariaID(@Param("id")String id);
    
    
}

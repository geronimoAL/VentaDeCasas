
package com.vivienda.venta.repositorio;

import com.vivienda.venta.entidades.Vivienda;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;



@Repository
public interface ViviendaRepository extends JpaRepository<Vivienda,String>{
    
    
    @Query("SELECT F From Vivienda F")
    public List<Vivienda>listaViviendas();
    
    @Query("SELECT F FROM Vivienda F WHERE F.provincia.nombre = :provincia")
    public List<Vivienda>casasXProvincia(@Param("provincia") String provincia);
    
    @Query("SELECT F FROM Vivienda F WHERE F.precio > :precio")
    public List<Vivienda>casasxPrecioMayorA(@Param("precio")String precio);
    
    @Query("SELECT F FROM Vivienda F WHERE F.precio < :precio")
    public List<Vivienda>casasxPrecioMenorA(@Param("precio")String precio);
    
    @Query("SELECT F FROM Vivienda F WHERE F.id = :id")
    public Vivienda viviendaId(@Param("id")String id);
    
    @Query("SELECT F FROM Vivienda F WHERE F.inmobiliaria.nombre = :inmobiliaria")
    public List<Vivienda>listaCasasInmobiliaria(@Param("inmobiliaria")String inmobiliaria);
    
}

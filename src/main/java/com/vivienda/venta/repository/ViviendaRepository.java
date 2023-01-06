
package com.vivienda.venta.repository;

import com.vivienda.venta.domain.Vivienda;
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
    
    @Query("SELECT F FROM Vivienda F WHERE F.usuario.id = :usuario")
    public List<Vivienda>casasXUsuario(@Param("usuario") String usuario);
    
    @Query("SELECT F FROM Vivienda F WHERE F.precio > :precio")
    public List<Vivienda>casasxPrecioMayorA(@Param("precio")String precio);
    
    @Query("SELECT F FROM Vivienda F WHERE F.precio < :precio")
    public List<Vivienda>casasxPrecioMenorA(@Param("precio")String precio);
    
    @Query("SELECT F FROM Vivienda F WHERE F.id = :id")
    public Vivienda viviendaId(@Param("id")String id);
    
    @Query("SELECT F FROM Vivienda F WHERE F.inmobiliaria.nombre = :inmobiliaria")
    public List<Vivienda>listaCasasInmobiliaria(@Param("inmobiliaria")String inmobiliaria);
    
    @Query("SELECT F FROM Vivienda F WHERE F.precio = :precio or F.banio = :banio or F.cochera = :cochera or F.dormitorio = :dormitorio or F.mt = :mt or F.ambiente = :ambiente or F.barrio = :barrio or F.ubicacion = :ubicacion or F.provincia.id = :provincia and F.inmobiliaria.id = :inmobiliaria")
    public List<Vivienda>filtrado(@Param("precio")String precio,@Param("banio")String banio,@Param("cochera")String cochera,
            @Param("dormitorio")String dormitorio,@Param("mt")String mt,@Param("ambiente")String ambiente,@Param("barrio")String barrio,
            @Param("ubicacion")String ubicacion,@Param("provincia")String provincia,@Param("inmobiliaria")String inmobiliaria);
    
}
/*

@RequestParam(required = false) String precio,@RequestParam(required = false) String banio,@RequestParam(required = false) String cochera,
@RequestParam(required = false) String dormitorio,@RequestParam(required = false) String mt, @RequestParam(required = false) String ambiente,
@RequestParam(required = false) String barrio, @RequestParam(required = false) String ubicacion,@RequestParam(required = false) String provincia,
@RequestParam(required = false) String inmobiliaria

*/
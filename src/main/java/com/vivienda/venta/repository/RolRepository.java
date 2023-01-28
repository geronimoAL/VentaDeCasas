
package com.vivienda.venta.repository;



import com.vivienda.venta.domain.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;



@Repository
public interface RolRepository extends JpaRepository<Rol, String> {
    
    @Query("SELECT c FROM Rol c WHERE c.name = :name")
    public Rol buscarPorNombre(@Param("name") String name);
}

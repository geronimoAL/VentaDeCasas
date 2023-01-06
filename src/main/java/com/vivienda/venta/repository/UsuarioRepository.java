
package com.vivienda.venta.repository;

import com.vivienda.venta.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;



@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,String> {
    
    @Query("SELECT F FROM Usuario F WHERE F.correo = :correo")
    public Usuario buscarXMail(@Param("correo")String correo);
}

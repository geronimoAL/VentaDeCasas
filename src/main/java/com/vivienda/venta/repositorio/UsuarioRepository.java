
package com.vivienda.venta.repositorio;

import com.vivienda.venta.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,String> {
    
}


package com.vivienda.venta.repositorio;

import com.vivienda.venta.entidades.Foto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;





@Repository
public interface FotoRepository extends JpaRepository<Foto,String>{
    
}

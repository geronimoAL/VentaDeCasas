
package com.vivienda.venta.repository;

import com.vivienda.venta.domain.Foto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;





@Repository
public interface FotoRepository extends JpaRepository<Foto,String>{
    
}

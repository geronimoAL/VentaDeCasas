
package com.vivienda.venta.repositorio;

import com.vivienda.venta.entidades.Provincia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProvinciaRepository extends JpaRepository<Provincia,String>{
    
}

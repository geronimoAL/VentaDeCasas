
package com.vivienda.venta.servicios;

import com.vivienda.venta.entidades.Provincia;
import com.vivienda.venta.repositorio.ProvinciaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
public class ProvinciaServicio {
    @Autowired
    public ProvinciaRepository provinciarepositorio;
//  
     //buscar el usuario x su id
       @Transactional(readOnly = true)
     public Provincia buscarID(String id){
         Provincia provincia=provinciarepositorio.findById(id).get();
         return provincia;
     }
     
     @Transactional(readOnly = true)
     public List<Provincia>lista(){
         List<Provincia>lis=provinciarepositorio.findAll();
         return lis;
     }
    
}


package com.vivienda.venta.service;

import com.vivienda.venta.domain.Foto;
import com.vivienda.venta.domain.Vivienda;
import com.vivienda.venta.errors.ErrorServicio;
import com.vivienda.venta.repository.FotoRepository;
import com.vivienda.venta.repository.ViviendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;




@Service
public class FotoServicio {
    
    @Autowired
    public FotoRepository fotorepository;
    
    @Autowired
    public ViviendaRepository viviendarepository;
            
   
    public Foto guardar(MultipartFile archivo){
        if (archivo != null) {
            try{
            Foto foto=new Foto();
            foto.setNombre(archivo.getName());
            foto.setMime(archivo.getContentType());
            foto.setContenido(archivo.getBytes());
            return fotorepository.save(foto);
            }catch(Exception e){
                System.out.println(e.getMessage());
            }
        }
        
        return null;
    }
    
    public Foto modificar(String id,MultipartFile archivo){
        if (archivo != null) {
           try{
              Foto fot=new Foto();
               if (id !=null) {
                   Vivienda vivi=viviendarepository.getById(id);
                   fot.setNombre(archivo.getName());
                   fot.setContenido(archivo.getBytes());
                   fot.setMime(archivo.getContentType());
                   return fotorepository.save(fot);
                   
               }
               
               
           }catch(Exception e){
               System.err.println("la foto no se cargó "+e.getMessage());
           }
            
        }
 
        return null;
    }
    
}

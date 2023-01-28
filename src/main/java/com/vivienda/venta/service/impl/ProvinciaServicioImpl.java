package com.vivienda.venta.service.impl;

import com.vivienda.venta.domain.Provincia;
import com.vivienda.venta.errors.ErrorServicio;
import com.vivienda.venta.repository.ProvinciaRepository;
import com.vivienda.venta.service.ProvinciaServicio;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProvinciaServicioImpl implements ProvinciaServicio {

    @Autowired
    public ProvinciaRepository provinciarepositorio;

    public Provincia buscarID(String id) {
        Provincia provincia = provinciarepositorio.findById(id).get();
        return provincia;
    }
    
    @Transactional
    public void crear(Provincia provincia) throws ErrorServicio {
        if(buscarNombre(provincia.getNombre())){
             throw new ErrorServicio("Esa provincia ya existe en la base de datos");
        }
        provinciarepositorio.save(provincia);
    }
    @Transactional
    public void modificar(Provincia provincia) throws ErrorServicio {
        if(buscarNombre(provincia.getNombre())){
             throw new ErrorServicio("Esa provincia ya existe en la base de datos");
        }
        Provincia provinciaBuscada=provinciarepositorio.getById(provincia.getId());
        provinciaBuscada.setNombre(provincia.getNombre());
        provinciarepositorio.save(provinciaBuscada);
        
    }
    @Transactional
    public void eliminar (String id) throws ErrorServicio {
        boolean provinciaExiste=provinciarepositorio.existsById(id);
        if (provinciaExiste == false) {
            throw new ErrorServicio("No se encontró la provincia a eliminar");
        }
        Provincia provinciaBuscada=provinciarepositorio.getById(id);
        provinciarepositorio.delete(provinciaBuscada);
    }
    @Transactional(readOnly = true)
    public List<Provincia> lista() {
        List<Provincia> lis = provinciarepositorio.findAll();
        return lis;
    }
    @Transactional(readOnly = true)
    public Boolean buscarNombre(String nombre){
        Provincia provincia =provinciarepositorio.buscarXNombre(nombre);
        if (provincia !=null) {
            return true;
        }else{
        return false;
        }
    }
   
}

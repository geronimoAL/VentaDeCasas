package com.vivienda.venta.service.impl;

import com.vivienda.venta.domain.Provincia;
import com.vivienda.venta.repository.ProvinciaRepository;
import com.vivienda.venta.service.ProvinciaServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProvinciaServicioImpl implements ProvinciaServicio {

    @Autowired
    public ProvinciaRepository provinciarepositorio;
//  
    //buscar el usuario x su id

    @Transactional(readOnly = true)
    public Provincia buscarID(String id) {
        Provincia provincia = provinciarepositorio.findById(id).get();
        return provincia;
    }

    @Transactional(readOnly = true)
    public List<Provincia> lista() {
        List<Provincia> lis = provinciarepositorio.findAll();
        return lis;
    }
}

package com.vivienda.venta.service.impl;

import com.vivienda.venta.domain.Rol;
import com.vivienda.venta.repository.RolRepository;
import com.vivienda.venta.service.RolServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class RolServicioImpl implements RolServicio{

    @Autowired
    public RolRepository rolRepositorio;
    
    @Override
    public List<Rol> listAll() {
     return rolRepositorio.findAll();
    }
    
}

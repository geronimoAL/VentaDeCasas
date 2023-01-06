package com.vivienda.venta.service;

import com.vivienda.venta.domain.Provincia;
import java.util.List;


public interface ProvinciaServicio {
     public Provincia buscarID(String id);
     
     public List<Provincia>lista();
}
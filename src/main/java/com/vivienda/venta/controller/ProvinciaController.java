
package com.vivienda.venta.controller;

import com.vivienda.venta.domain.Provincia;
import com.vivienda.venta.service.impl.ProvinciaServicioImpl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@RequestMapping("/provincia")
@Controller
public class ProvinciaController {
    
    @Autowired
    private ProvinciaServicioImpl provinciaServicioImpl;
    
    @GetMapping("/lista")
    public String listaProvincia(ModelMap modelo){
        List<Provincia> lista = provinciaServicioImpl.lista();
        modelo.put("lista", lista);
        return "listaProvincia.html";
    }
    
    
}

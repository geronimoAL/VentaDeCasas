
package com.vivienda.venta.controlador;

import com.vivienda.venta.entidades.Vivienda;
import com.vivienda.venta.servicios.ViviendaServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;





@Controller
public class PortalControlador {
    @Autowired
    private ViviendaServicio viviendaservicio;
     
    @GetMapping("/")
    public String index(ModelMap modelo){
        List<Vivienda>list=viviendaservicio.ListaDeViviendas();
        modelo.put("list",list);
        return "index.html";
    }
    
}

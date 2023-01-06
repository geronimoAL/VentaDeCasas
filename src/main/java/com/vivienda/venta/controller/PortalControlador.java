package com.vivienda.venta.controller;

import com.vivienda.venta.domain.Inmobiliaria;
import com.vivienda.venta.domain.Provincia;
import com.vivienda.venta.domain.Vivienda;
import com.vivienda.venta.service.InmobiliariaServicioImpl;
import com.vivienda.venta.service.ProvinciaServicioImpl;
import com.vivienda.venta.service.ViviendaServicioImpl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class PortalControlador {

    @Autowired
    private ViviendaServicioImpl viviendaServicioImpl;
    @Autowired
    private ProvinciaServicioImpl provinciaServicioImpl;
    @Autowired
    private InmobiliariaServicioImpl inmobiliariaServicioImpl;

    @GetMapping("/")
    public String index(ModelMap modelo) {
        List<Vivienda> list = viviendaServicioImpl.ListaDeViviendas();
        modelo.put("list", list);
        return "index.html";
    }

    @GetMapping("/inicio")
    public String inicio(ModelMap modelo,RedirectAttributes attributes) {
        List<Vivienda> lista = viviendaServicioImpl.ListaDeViviendas();
        attributes.addFlashAttribute("accion", "total");
        modelo.put("lista", lista);
        return "inicio.html";
    }

    @GetMapping("/login")
    public String login(ModelMap modelo, @RequestParam(required = false) String error) {
        if (error != null) {
            modelo.put("error", "correo o clave incorrecto");
        }
        return "usuario.html";
    }

    @GetMapping("/busqueda")
    public String busqueda(ModelMap modelo) {
        List<Provincia> lista = provinciaServicioImpl.lista();
        List<Inmobiliaria> inmobi = inmobiliariaServicioImpl.listaDeInmobiliarias();
        modelo.put("inmobi", inmobi);
        modelo.put("list", lista);
        return "busqueda.html";
    }
    @GetMapping("filtrar")
    public String filtrado(ModelMap modelo,@RequestParam(required = false) String precio,@RequestParam(required = false) String banio,@RequestParam(required = false) String cochera,@RequestParam(required = false) String dormitorio,@RequestParam(required = false) String mt, @RequestParam(required = false) String ambiente,@RequestParam(required = false) String barrio, @RequestParam(required = false) String ubicacion,@RequestParam String provincia,@RequestParam String inmobiliaria){
        List<Vivienda>lista=viviendaServicioImpl.filtrado(precio,banio,cochera,dormitorio,mt,ambiente,barrio,ubicacion,provincia,inmobiliaria);
        modelo.put("lista_total","filtrado");
        modelo.put("accion", "filtrado");
         modelo.put("lista",lista);
        return "list_vivienda.html";
    }
}

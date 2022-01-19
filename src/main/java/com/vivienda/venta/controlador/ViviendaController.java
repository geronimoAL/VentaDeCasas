package com.vivienda.venta.controlador;

import com.vivienda.venta.entidades.Inmobiliaria;
import com.vivienda.venta.entidades.Provincia;
import com.vivienda.venta.entidades.Vivienda;
import com.vivienda.venta.errores.ErrorServicio;
import com.vivienda.venta.servicios.InmobiliariaServicio;
import com.vivienda.venta.servicios.ProvinciaServicio;
import com.vivienda.venta.servicios.ViviendaServicio;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
//import javax.validation.Valid;

@Controller
@RequestMapping("/vivienda")
public class ViviendaController {

    @Autowired
    private ViviendaServicio viviendaservicio;
    @Autowired
    private ProvinciaServicio provinciaservicio;
    @Autowired
    private InmobiliariaServicio inmobiliariaservicio;

    @GetMapping("/crear")
    public String crear(ModelMap modelo) {
        Vivienda vivienda = new Vivienda();
        List<Provincia> lista = provinciaservicio.lista();
        List<Inmobiliaria> inmobiliaria = inmobiliariaservicio.listaDeInmobiliarias();
        modelo.put("inmobiliarias", inmobiliaria);
        modelo.put("vivienda", vivienda);
        modelo.put("list", lista);
        modelo.put("accion", "creacion");
        return "crear_vivienda.html";
    }

    @PostMapping("/creacion")
    public String creacion(@ModelAttribute Vivienda vivienda, MultipartFile archivo, MultipartFile archivo1, MultipartFile archivo2, MultipartFile archivo3, ModelMap modelo) {
        Vivienda vivi = new Vivienda();
        try {
            viviendaservicio.crear(vivienda, archivo, archivo1, archivo2, archivo3);
            return "redirect:/";
        } catch (ErrorServicio e) {
            List<Provincia> lista = provinciaservicio.lista();
            List<Inmobiliaria> inmobi = inmobiliariaservicio.listaDeInmobiliarias();
            modelo.put("inmobi", inmobi);
            modelo.put("list", lista);
            modelo.put("vivienda", vivi);
            modelo.put("mal", e.getMessage());
            modelo.put("accion", "creacion");
            return "redirect:/inmobiliaria/crear";
        }

    }

    @GetMapping("/modificar/{id}")
    public String modificacion(@PathVariable String id, ModelMap modelo) throws ErrorServicio {
        Vivienda vivi = viviendaservicio.BuscarXId(id);
        List<Provincia> lista = provinciaservicio.lista();
        List<Inmobiliaria> inmobiliaria = inmobiliariaservicio.listaDeInmobiliarias();
        modelo.put("inmobiliarias", inmobiliaria);
        modelo.put("list", lista);
        modelo.put("vivienda", vivi);
        modelo.put("accion", "modificacion");
        return "crear_vivienda.html";
    }

    @PostMapping("/modificacion")
    public String modificar(ModelMap modelo, @ModelAttribute Vivienda vivienda, MultipartFile archivo, MultipartFile archivo1, MultipartFile archivo2, MultipartFile archivo3) throws ErrorServicio {
        Vivienda vivi = viviendaservicio.BuscarXId(vivienda.getId());
        try {
            viviendaservicio.modificar(vivienda, archivo, archivo1, archivo2, archivo3);
//            return "inicio.html";
            return "redirect:/";
        } catch (ErrorServicio e) {
            List<Provincia> lista = provinciaservicio.lista();
            List<Inmobiliaria> inmobiliaria = inmobiliariaservicio.listaDeInmobiliarias();
            modelo.put("inmobiliarias", inmobiliaria);
            modelo.put("list", lista);
            modelo.put("mal", e.getMessage());
            modelo.put("vivienda", vivi);
            return "redirect:/inmobiliaria/" + vivi.getId();
        }
    }

    @GetMapping("/lista")
    public String lista(ModelMap modelo) {
        List<Vivienda> lista = viviendaservicio.ListaDeViviendas();
        modelo.put("lista", lista);
        return "list_vivienda.html";
    }

    @GetMapping("/lista_modificar")
    public String listaM(ModelMap modelo) {
        List<Vivienda> lista = viviendaservicio.ListaDeViviendas();
        modelo.put("lista", lista);
        modelo.put("accion", "modificacion");
        return "list_vivienda.html";
    }

}

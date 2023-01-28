package com.vivienda.venta.controller;

import com.vivienda.venta.domain.Inmobiliaria;
import com.vivienda.venta.domain.Provincia;
import com.vivienda.venta.errors.ErrorServicio;
import com.vivienda.venta.service.impl.InmobiliariaServicioImpl;
import com.vivienda.venta.service.impl.ProvinciaServicioImpl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

@RequestMapping("/provincia")
@Controller
public class ProvinciaController {

    @Autowired
    private ProvinciaServicioImpl provinciaServicioImpl;
    @Autowired
    private InmobiliariaServicioImpl inmobiliariaServicioImpl;

    @GetMapping("/lista")
    public String listaProvincia(ModelMap modelo) {
        List<Provincia> lista = provinciaServicioImpl.lista();
        modelo.put("lista", lista);
        return "listaProvincia.html";
    }

    @GetMapping("/crear")
    public String crearProvincia(ModelMap modelo) {
        Provincia provincia = new Provincia();
        modelo.put("provincia", provincia);
        modelo.put("accion", "crear");
        return "crear_provincia.html";
    }

    @PostMapping("/creacion")
    public String creacion(ModelMap modelo, @ModelAttribute Provincia provincia, RedirectAttributes redirectAttributes) {
        List<Inmobiliaria> listaInmobiliarias = null;
        List<Provincia> listaProvincias = null;
        try {
            provinciaServicioImpl.crear(provincia);
            listaInmobiliarias = inmobiliariaServicioImpl.listaDeInmobiliarias();
            listaProvincias = provinciaServicioImpl.lista();
            modelo.put("listaInmo", listaInmobiliarias);
            modelo.put("listaPro", listaProvincias);
            return "redirect:/admin/home";
        } catch (ErrorServicio e) {
            redirectAttributes.addFlashAttribute("mal", e.getMessage());
            return "redirect:/provincia/crear";
        }

    }

    @GetMapping("/modificacion/{id}")
    public String modificarProvincia(ModelMap modelo, @PathVariable String id) {
        Provincia provincia = provinciaServicioImpl.buscarID(id);
        modelo.put("provincia", provincia);
        modelo.put("accion", "modificacion");
        return "crear_provincia.html";
    }

    @PostMapping("/modificar")
    public String modificaciónProvincia(ModelMap modelo, @ModelAttribute Provincia provincia, RedirectAttributes redirectAttributes) {
        Provincia provinciaBuscada = null;
        List<Inmobiliaria> listaInmobiliarias = null;
        List<Provincia> listaProvincias = null;
        try {
            provinciaServicioImpl.modificar(provincia);
            listaInmobiliarias = inmobiliariaServicioImpl.listaDeInmobiliarias();
            listaProvincias = provinciaServicioImpl.lista();
            modelo.put("listaInmo", listaInmobiliarias);
            modelo.put("listaPro", listaProvincias);
            return "redirect:/admin/home";
        } catch (ErrorServicio e) {
//            listaInmobiliarias = inmobiliariaServicioImpl.listaDeInmobiliarias();
//            listaProvincias = provinciaServicioImpl.lista();
//            modelo.put("listaInmo", listaInmobiliarias);
//            modelo.put("listaPro", listaProvincias);
            provinciaBuscada = provinciaServicioImpl.buscarID(provincia.getId());
            redirectAttributes.addFlashAttribute("mal", e.getMessage());
            return "redirect:/provincia/modificacion/" + provinciaBuscada.getId();
        }
    }

    @GetMapping("/delete/{id}")
    public String eliminarProvincia(@PathVariable String id, RedirectAttributes redirectAttributes,ModelMap modelo) {
        List<Inmobiliaria> listaInmobiliarias = null;
        List<Provincia> listaProvincias = null;
        try {
            listaInmobiliarias = inmobiliariaServicioImpl.listaDeInmobiliarias();
            listaProvincias = provinciaServicioImpl.lista();
            modelo.put("listaInmo", listaInmobiliarias);
            modelo.put("listaPro", listaProvincias);
            provinciaServicioImpl.eliminar(id);
            return "redirect:/admin/home";
        } catch (ErrorServicio e) {
            listaInmobiliarias = inmobiliariaServicioImpl.listaDeInmobiliarias();
            listaProvincias = provinciaServicioImpl.lista();
            modelo.put("listaInmo", listaInmobiliarias);
            modelo.put("listaPro", listaProvincias);
            redirectAttributes.addAttribute("mal", e.getMessage());
            return "redirect:/admin/home";
        }

    }
}

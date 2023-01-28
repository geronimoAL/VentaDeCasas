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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/inmobiliaria")
public class InmobiliariaController {

    @Autowired
    private InmobiliariaServicioImpl inmobiliariaServicioImpl;
    @Autowired
    private ProvinciaServicioImpl provinciaServicioImpl;

    @GetMapping("/crear")
    public String crear(ModelMap modelo) {
        Inmobiliaria inmo = new Inmobiliaria();
        modelo.put("inmo", inmo);
        modelo.put("accion", "crear");
        return "crear_inmo.html";
    }

    @PostMapping("/creacion")
    public String creacion(ModelMap modelo, @ModelAttribute Inmobiliaria inmo, MultipartFile archivo, RedirectAttributes redirectAttributes) {
        Inmobiliaria inmob = new Inmobiliaria();
        List<Inmobiliaria> listaInmobiliarias = null;
        List<Provincia> listaProvincias = null;
        try {
            inmobiliariaServicioImpl.crear(inmo, archivo);
            listaInmobiliarias = inmobiliariaServicioImpl.listaDeInmobiliarias();
            listaProvincias = provinciaServicioImpl.lista();
            modelo.put("listaInmo", listaInmobiliarias);
            modelo.put("listaPro", listaProvincias);
            return "redirect:/admin/home";
        } catch (ErrorServicio e) {
            modelo.put("inmo", inmob);
            redirectAttributes.addFlashAttribute("mal", e.getMessage());
            return "redirect:/inmobiliaria/crear";
        }

    }

    @GetMapping("/modificar/{id}")
    public String modificacion(@PathVariable String id, ModelMap modelo) {
        Inmobiliaria inmo = inmobiliariaServicioImpl.buscarXID(id);
        modelo.put("inmo", inmo);
        modelo.put("accion", "modificacion");
        return "crear_inmo";
    }

    @PostMapping("/modificacion")
    public String modificar(ModelMap modelo, @ModelAttribute Inmobiliaria inmobiliaria, MultipartFile archivo, RedirectAttributes redirectAttributes) {
        Inmobiliaria inmo = inmobiliariaServicioImpl.buscarXID(inmobiliaria.getId());
        List<Inmobiliaria> listaInmobiliarias = null;
        List<Provincia> listaProvincias = null;
        try {
            inmobiliariaServicioImpl.modificacion(inmobiliaria, archivo);
            listaInmobiliarias = inmobiliariaServicioImpl.listaDeInmobiliarias();
            listaProvincias = provinciaServicioImpl.lista();
            modelo.put("listaInmo", listaInmobiliarias);
            modelo.put("listaPro", listaProvincias);
            return "redirect:/admin/home";
        } catch (ErrorServicio e) {
            redirectAttributes.addFlashAttribute("mal", e.getMessage());
            modelo.put("inmo", inmo);
            return "redirect:/inmobiliaria/modificar/" + inmo.getId();
        }
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarInmobiliaria(@PathVariable String id, ModelMap modelo,RedirectAttributes redirectAttributes) {
        List<Inmobiliaria> listaInmobiliarias = null;
        List<Provincia> listaProvincias = null;
        try {
            inmobiliariaServicioImpl.eliminar(id);
            listaInmobiliarias = inmobiliariaServicioImpl.listaDeInmobiliarias();
            listaProvincias = provinciaServicioImpl.lista();
            modelo.put("listaInmo", listaInmobiliarias);
            modelo.put("listaPro", listaProvincias);
            return "redirect:/admin/home";
        } catch (ErrorServicio e) {
            listaInmobiliarias = inmobiliariaServicioImpl.listaDeInmobiliarias();
            listaProvincias = provinciaServicioImpl.lista();
            modelo.put("listaInmo", listaInmobiliarias);
            modelo.put("listaPro", listaProvincias);
            redirectAttributes.addFlashAttribute("mal", e.getMessage());
            return "redirect:/admin/home";
        }
    }
}

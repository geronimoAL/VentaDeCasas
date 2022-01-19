package com.vivienda.venta.controlador;

import com.vivienda.venta.entidades.Inmobiliaria;
import com.vivienda.venta.errores.ErrorServicio;
import com.vivienda.venta.servicios.InmobiliariaServicio;
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

@Controller
@RequestMapping("/inmobiliaria")
public class InmobiliariaController {

    @Autowired
    private InmobiliariaServicio inmobiliariaservicio;

    @GetMapping("/crear")
    public String crear(ModelMap modelo) {
        Inmobiliaria inmo = new Inmobiliaria();
        modelo.put("inmo", inmo);
        modelo.put("accion", "crear");
        return "crear_inmo.html";
    }

    @PostMapping("/creacion")
    public String creacion(ModelMap modelo, @ModelAttribute Inmobiliaria inmo, MultipartFile archivo) {
        Inmobiliaria inmob = new Inmobiliaria();
        try {
            inmobiliariaservicio.crear(inmo, archivo);
            return "redirect:/";
        } catch (ErrorServicio e) {
            modelo.put("inmo", inmob);
            modelo.put("mal", e.getMessage());
            return "redirect:/inmobiliaria/crear";
        }

    }

    @GetMapping("/modificar/{id}")
    public String modificacion(@PathVariable String id, ModelMap modelo) {
        Inmobiliaria inmo = inmobiliariaservicio.buscarXID(id);
        modelo.put("usuario", inmo);
        modelo.put("accion", "modificat");
        return "crear_inmo";
    }

    @PostMapping("/modificacion")
    public String modificar(ModelMap modelo, @RequestParam String id,@RequestParam String nombre, MultipartFile foto) {
        Inmobiliaria inmo = inmobiliariaservicio.buscarXID(id);
        try {
           inmobiliariaservicio.modificacion(id, nombre, foto);
          return "redirect:/usuario/inicio.html";
        } catch (ErrorServicio e) {
            modelo.put("mal", e.getMessage());
            modelo.put("usuario", inmo);
            return "crear_usu.html";
        }
    }

    
}

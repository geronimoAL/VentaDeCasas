package com.vivienda.venta.controller;

import com.vivienda.venta.domain.Inmobiliaria;
import com.vivienda.venta.domain.Provincia;
import com.vivienda.venta.domain.Rol;
import com.vivienda.venta.domain.Usuario;
import com.vivienda.venta.domain.Vivienda;
import com.vivienda.venta.errors.ErrorServicio;
import com.vivienda.venta.service.RolServicio;
import com.vivienda.venta.service.impl.InmobiliariaServicioImpl;
import com.vivienda.venta.service.impl.ProvinciaServicioImpl;
import com.vivienda.venta.service.impl.RolServicioImpl;
import com.vivienda.venta.service.impl.UsuarioServicioImpl;
import com.vivienda.venta.service.impl.ViviendaServicioImpl;
import java.util.List;
import javax.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/admin")
@Controller
public class AdminController {

    @Autowired
    private UsuarioServicioImpl usuarioServicioImpl;
    @Autowired
    private ViviendaServicioImpl viviendaServicioImpl;
    @Autowired
    private RolServicioImpl rolServicioImpl;
    @Autowired
    private InmobiliariaServicioImpl inmobiliariaServicioImpl;
    @Autowired
    private ProvinciaServicioImpl provinciaServicioImpl;

     @GetMapping("/home")
    public String panelAdmin(ModelMap modelo) {
        List<Inmobiliaria>listaInmobiliarias=inmobiliariaServicioImpl.listaDeInmobiliarias();
        List<Provincia>listaProvincias=provinciaServicioImpl.lista();
        modelo.put("listaInmo",listaInmobiliarias);
        modelo.put("listaPro",listaProvincias);
        return "admin.html";
    }
    
    @GetMapping("/role")
    public String cambiarRoles(ModelMap modelo) {
        List<Usuario> lista = usuarioServicioImpl.lista();
        modelo.put("listaUsuarios", lista);
        return "dashboard.html";
    }

    @GetMapping("/edit/{id}")
    public String editUser(@PathVariable("id") String id, ModelMap model) throws ErrorServicio {
//        String idBase = String.valueOf(id);
        Usuario user = usuarioServicioImpl.buscarID(id);
        List<Rol> listRoles = rolServicioImpl.listAll();
        model.addAttribute("usuario", user);
        model.addAttribute("listaRoles", listRoles);
        return "usuarios.html";
    }

    @PostMapping("/usuario/save")
    public String saveUser(Usuario user, ModelMap model) throws ErrorServicio, MessagingException {
//        try {
            usuarioServicioImpl.cambiarRol(user);
            List<Vivienda> lista = viviendaServicioImpl.ListaDeViviendas();
//            attributes.addFlashAttribute("accion", "total");
            model.put("lista", lista);
            return "inicio.html";
//        } catch (ErrorServicio ex) {
//            List<Usuario> lista = usuarioServicioImpl.lista();
//            model.put("listaUsuarios", lista);
//            return "dashboard.html";
//        }

    }

}

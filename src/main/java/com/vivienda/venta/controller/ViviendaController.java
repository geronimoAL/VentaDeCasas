package com.vivienda.venta.controller;

import com.vivienda.venta.domain.Inmobiliaria;
import com.vivienda.venta.domain.Provincia;
import com.vivienda.venta.domain.Usuario;
import com.vivienda.venta.domain.Vivienda;
import com.vivienda.venta.errors.ErrorServicio;
import com.vivienda.venta.service.impl.InmobiliariaServicioImpl;
import com.vivienda.venta.service.impl.ProvinciaServicioImpl;
import com.vivienda.venta.service.impl.UsuarioServicioImpl;
import com.vivienda.venta.service.impl.ViviendaServicioImpl;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;
//import javax.validation.Valid;

@Controller
@RequestMapping("/vivienda")
public class ViviendaController {

    @Autowired
    private ViviendaServicioImpl viviendaServicioImpl;
    @Autowired
    private ProvinciaServicioImpl provinciaServicioImpl;
    @Autowired
    private InmobiliariaServicioImpl inmobiliariaServicioImpl;
    @Autowired
    private UsuarioServicioImpl usuarServicioImpl;

    @GetMapping("/crear/{id}")
    public ModelAndView crear(ModelMap modelo, @PathVariable String id) throws ErrorServicio {
        Vivienda vivienda = new Vivienda();
        List<Provincia> lista = provinciaServicioImpl.lista();
        List<Inmobiliaria> inmobiliaria = inmobiliariaServicioImpl.listaDeInmobiliarias();
        Usuario usuario = usuarServicioImpl.buscarID(id);
        modelo.put("usuario", usuario);
        modelo.put("inmobiliarias", inmobiliaria);
        modelo.put("vivienda", vivienda);
        modelo.put("list", lista);
        modelo.put("accion", "creacion");
        ModelAndView mav = new ModelAndView("crear_vivienda.html");
        return mav;
    }

    @PostMapping("/creacion")
    public String creacion(RedirectAttributes redirectAttributes, @ModelAttribute Vivienda vivienda, MultipartFile archivo, MultipartFile archivo1, MultipartFile archivo2, MultipartFile archivo3, @RequestParam String usuario, ModelMap modelo) {
        Vivienda vivi = new Vivienda();
        try {
            viviendaServicioImpl.crear(vivienda, archivo, archivo1, archivo2, archivo3, usuario);
            return "redirect:/inicio";
        } catch (ErrorServicio e) {
            List<Provincia> lista = provinciaServicioImpl.lista();
            List<Inmobiliaria> inmobi = inmobiliariaServicioImpl.listaDeInmobiliarias();
            modelo.put("inmobiliarias", inmobi);
            modelo.put("list", lista);
            modelo.put("vivienda", vivi);
            redirectAttributes.addFlashAttribute("mal", e.getMessage());
            System.err.println("mensaje error " + e.getMessage());
            modelo.put("accion", "creacion");
            Usuario usuarioBuscado = usuarServicioImpl.buscarID(usuario);
            modelo.put("usuario", usuarioBuscado);
            return "redirect:/vivienda/crear/" + usuarioBuscado.getId();
        }

    }

    @GetMapping("/modificar/{id}")
    public String modificacion(@PathVariable String id, ModelMap modelo) throws ErrorServicio {
        Vivienda vivi = viviendaServicioImpl.BuscarXId(id);
        List<Provincia> lista = provinciaServicioImpl.lista();
        List<Inmobiliaria> inmobiliaria = inmobiliariaServicioImpl.listaDeInmobiliarias();
        Usuario usuario=usuarServicioImpl.buscarID(vivi.getUsuario().getId());
        modelo.put("inmobiliarias", inmobiliaria);
        modelo.put("list", lista);
        modelo.put("vivienda", vivi);
        modelo.put("accion", "modificacion");
        modelo.put("usuario",usuario);
        return "crear_vivienda.html";
    }

    @PostMapping("/modificacion")
    public String modificar(RedirectAttributes redirectAttributes, ModelMap modelo, @ModelAttribute Vivienda vivienda, MultipartFile archivo, MultipartFile archivo1, MultipartFile archivo2, MultipartFile archivo3) throws ErrorServicio {
        Vivienda vivi = viviendaServicioImpl.BuscarXId(vivienda.getId());
        try {
            viviendaServicioImpl.modificar(vivienda, archivo, archivo1, archivo2, archivo3);
            return "redirect:/inicio";
        } catch (ErrorServicio e) {
            List<Provincia> lista = provinciaServicioImpl.lista();
            List<Inmobiliaria> inmobiliaria = inmobiliariaServicioImpl.listaDeInmobiliarias();
            redirectAttributes.addFlashAttribute("mal", e.getMessage());
            return "redirect:/vivienda/modificar/" + vivi.getId();
        }
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable String id) {
        viviendaServicioImpl.eliminar(id);
        return "redirect:/vivienda/lista_modificar";
    }

    //lista x usuario
    @GetMapping("/listaUsuario/{id}")
    public String lista(ModelMap modelo, @PathVariable String id) throws ErrorServicio {
        List<Vivienda> lista = viviendaServicioImpl.ListaDeViviendasXUsuario(id);
        Usuario usu = usuarServicioImpl.buscarID(id);
        modelo.put("usuario", usu);
        modelo.put("lista", lista);
        modelo.put("lista_total", "listaUsuario");
        modelo.put("accion", "modificacion");
        return "list_vivienda.html";
    }

    //lista total
    @GetMapping("/lista")
    public ModelAndView lista(ModelMap modelo, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("list_vivienda.html");
        Map<String, ?> map = RequestContextUtils.getInputFlashMap(request);
        if (map != null) {
            mav.addObject("accion", map.get("accion"));
        }
        List<Vivienda> lista = viviendaServicioImpl.ListaDeViviendas();
        modelo.put("lista", lista);
        modelo.put("lista_total", "listaDefinitiva");
//        modelo.put("accion", "filtrado");
        return mav;
    }
    
    
    
    /*
     @GetMapping("/")
    public ModelAndView index(HttpServletRequest request){
    ModelAndView mav=new ModelAndView("index.html");
    Map<String,?>map=RequestContextUtils.getInputFlashMap(request);
        if (map !=null) {
            mav.addObject("excelente", map.get("excelente"));
            mav.addObject("pesimo", map.get("pesimo"));
            mav.addObject("modificacion", map.get("modificacion"));
        }
    return mav;
   }
    
     */

//    @GetMapping("/lista_modificar")
//    public String listaM(ModelMap modelo) {
//        List<Vivienda> lista = viviendaservicio.ListaDeViviendas();
//        modelo.put("lista_total","modificar");
//        modelo.put("lista", lista);
//        modelo.put("accion", "modificacion");
//        return "list_vivienda.html";
//    }
}

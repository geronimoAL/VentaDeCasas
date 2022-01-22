package com.vivienda.venta.controlador;

import com.vivienda.venta.entidades.Inmobiliaria;
import com.vivienda.venta.entidades.Provincia;
import com.vivienda.venta.entidades.Usuario;
import com.vivienda.venta.entidades.Vivienda;
import com.vivienda.venta.errores.ErrorServicio;
import com.vivienda.venta.servicios.InmobiliariaServicio;
import com.vivienda.venta.servicios.ProvinciaServicio;
import com.vivienda.venta.servicios.UsuarioServicio;
import com.vivienda.venta.servicios.ViviendaServicio;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContextUtils;
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
    @Autowired
    private UsuarioServicio usuarioservicio;

    @GetMapping("/crear/{id}")
    public String crear(ModelMap modelo,@PathVariable String id) throws ErrorServicio {
        Vivienda vivienda = new Vivienda();
        List<Provincia> lista = provinciaservicio.lista();
        List<Inmobiliaria> inmobiliaria = inmobiliariaservicio.listaDeInmobiliarias();
        Usuario usuario =usuarioservicio.buscarID(id);
        modelo.put("usuario",usuario);
        modelo.put("inmobiliarias", inmobiliaria);
        modelo.put("vivienda", vivienda);
        modelo.put("list", lista);
        modelo.put("accion", "creacion");
        return "crear_vivienda.html";
    }

    @PostMapping("/creacion")
    public String creacion(@ModelAttribute Vivienda vivienda, MultipartFile archivo, MultipartFile archivo1, MultipartFile archivo2, MultipartFile archivo3,@RequestParam String usuario, ModelMap modelo) {
        Vivienda vivi = new Vivienda();
        try {
            viviendaservicio.crear(vivienda, archivo, archivo1, archivo2, archivo3,usuario);
            return "redirect:/inicio";
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
            return "redirect:/inicio";
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
    
    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable String id){
        viviendaservicio.eliminar(id);
        return "redirect:/vivienda/lista_modificar";
    }
    //lista x usuario
    @GetMapping("/listaUsuario/{id}")
    public String lista(ModelMap modelo,@PathVariable String id) throws ErrorServicio {
        List<Vivienda> lista = viviendaservicio.ListaDeViviendasXUsuario(id);
        Usuario usu=usuarioservicio.buscarID(id);
        modelo.put("usuario",usu);
        modelo.put("lista", lista);
        modelo.put("lista_total","listaUsuario");
        modelo.put("accion", "modificacion");
        return "list_vivienda.html";
    }
    //lista total
    @GetMapping("/lista")
    public ModelAndView lista(ModelMap modelo,HttpServletRequest request) {
        ModelAndView mav=new ModelAndView("list_vivienda.html");
        Map<String,?>map=RequestContextUtils.getInputFlashMap(request);
        if(map!= null){
            mav.addObject("accion", map.get("accion"));
        }
        List<Vivienda> lista = viviendaservicio.ListaDeViviendas();
        modelo.put("lista", lista);
        modelo.put("lista_total","listaDefinitiva");
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

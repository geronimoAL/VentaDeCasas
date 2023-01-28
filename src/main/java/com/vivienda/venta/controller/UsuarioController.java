package com.vivienda.venta.controller;

import com.vivienda.venta.domain.Usuario;
import com.vivienda.venta.domain.Vivienda;
import com.vivienda.venta.errors.ErrorServicio;
import com.vivienda.venta.service.impl.UsuarioServicioImpl;
import com.vivienda.venta.service.impl.ViviendaServicioImpl;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;


@Controller
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private ViviendaServicioImpl viviendaServicioImpl;
    @Autowired
    private UsuarioServicioImpl usServicioImpl;

    @GetMapping("/crear")
    public String crear(ModelMap modelo, HttpServletRequest request) {
        Usuario usuario = new Usuario();
        Map<String, ?> map = RequestContextUtils.getInputFlashMap(request);
        if (map != null) {
            modelo.put("mal", map.get("message"));
        }
        modelo.put("usuario", usuario);
        modelo.put("accion", "creacion");
        return "crear_usu.html";
    }

    @PostMapping("/creacion")
    public String creacion(ModelMap modelo, RedirectAttributes redirectAttributes, @ModelAttribute Usuario usuario, String clave1, HttpServletRequest request) throws ServletException {
        Usuario usu = new Usuario();
        try {
            usServicioImpl.crear(usuario, clave1);
            List<Vivienda> lista = viviendaServicioImpl.ListaDeViviendas();
            request.login(usuario.getCorreo(), usuario.getClave());
            modelo.put("lista", lista);
            return "inicio.html";
        } catch (ErrorServicio e) {
            modelo.put("usuario", usu);
            redirectAttributes.addFlashAttribute("message" , e.getMessage());
            return "redirect:/usuario/crear";
        }

    }

    @GetMapping("/modificar/{id}")
    public String modificacion(HttpSession session, @PathVariable String id, ModelMap modelo) throws ErrorServicio {
        Usuario login = (Usuario) session.getAttribute("usuariosession");
        if (login == null || !login.getId().equals(id)) {
            return "redirect:/inicio";
        }
        Usuario usu = usServicioImpl.buscarID(id);
        modelo.addAttribute("usuario", usu);
        modelo.put("accion", "modificacion");
        return "crear_usu";
    }

    @PostMapping("/modificacion")
    public String modificar(RedirectAttributes redirectAttributes,HttpSession session, ModelMap modelo, @ModelAttribute Usuario usuario, @RequestParam String clave1) throws ErrorServicio {
        Usuario usu = usServicioImpl.buscarID(usuario.getId());
        try {
            Usuario login = (Usuario) session.getAttribute("usuariosession");
            if (login == null || !login.getId().equals(usuario.getId())) {
                return "redirect:/inicio";
            }
            List<Vivienda> lista = viviendaServicioImpl.ListaDeViviendas();
            usServicioImpl.modificar(usuario, clave1);
            session.setAttribute("usuariosession", usu);
            modelo.put("lista", lista);
            return "inicio.html";
        } catch (ErrorServicio e) {
            redirectAttributes.addFlashAttribute("mal", e.getMessage());
             redirectAttributes.addFlashAttribute("usuario", usu);
            return "redirect:/usuario/modificar/" + usu.getId();
        }
    }

    @GetMapping("/inicio")
    public String inicio() {
        return "inicio.html";
    }
}

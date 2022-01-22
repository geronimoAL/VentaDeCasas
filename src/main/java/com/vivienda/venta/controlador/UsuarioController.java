package com.vivienda.venta.controlador;

import com.vivienda.venta.entidades.Usuario;
import com.vivienda.venta.errores.ErrorServicio;
import com.vivienda.venta.servicios.UsuarioServicio;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioServicio usuarioservicio;

    @GetMapping("/crear")
    public String crear(ModelMap modelo) {
        Usuario usuario = new Usuario();
        modelo.put("usuario", usuario);
        modelo.put("accion", "creacion");
        return "crear_usu.html";
    }

    @PostMapping("/creacion")
    public String creacion(ModelMap modelo, @ModelAttribute Usuario usuario, String clave1, HttpServletRequest request) throws ServletException {
        Usuario usu = new Usuario();
        try {
            usuarioservicio.crear(usuario, clave1);
            request.login(usuario.getCorreo(), usuario.getClave());
            return "inicio.html";
        } catch (ErrorServicio e) {
            modelo.put("usuario", usu);
            modelo.put("mal", e.getMessage());
            return "redirect:/usuario/crear";
        }

    }

    @GetMapping("/modificar/{id}")
    public String modificacion(HttpSession session, @PathVariable String id, ModelMap modelo) throws ErrorServicio {
         Usuario login = (Usuario) session.getAttribute("usuariosession");
        if (login == null || !login.getId().equals(id)) {
            return "redirect:/inicio";
        }
        Usuario usu = usuarioservicio.buscarID(id);
        modelo.addAttribute("usuario", usu);
        modelo.put("accion", "modificacion");
        return "crear_usu";
    }

    @PostMapping("/modificacion")
    public String modificar(HttpSession session, ModelMap modelo, @ModelAttribute Usuario usuario, @RequestParam String clave1) throws ErrorServicio {
        Usuario usu = usuarioservicio.buscarID(usuario.getId());
        try {
            Usuario login = (Usuario) session.getAttribute("usuariosession");
            if (login == null || !login.getId().equals(usuario.getId())) {
                return "redirect:/inicio";
            }
            usuarioservicio.modificar(usuario, clave1);
            session.setAttribute("usuariosession", usu);
            return "inicio.html";
        } catch (ErrorServicio e) {
            modelo.put("mal", e.getMessage());
            modelo.put("usuario", usu);
            return "redirect:/usuario/modificar/" + usu.getId();
        }
    }

    @GetMapping("/inicio")
    public String inicio() {
        return "inicio.html";
    }
}

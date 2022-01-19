package com.vivienda.venta.controlador;

import com.vivienda.venta.entidades.Usuario;
import com.vivienda.venta.errores.ErrorServicio;
import com.vivienda.venta.servicios.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
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
        modelo.put("accion", "crear");
        return "crear_usu.html";
    }

    @PostMapping("/creacion")
    public String creacion(ModelMap modelo, @RequestParam Usuario usuario, String clave1) {
        Usuario usu = new Usuario();
        try {
            usuarioservicio.crear(usuario, clave1);
            return "inicio.html";
        } catch (ErrorServicio e) {
            modelo.put("usuario", usu);
            modelo.put("mal", e.getMessage());
            return "redirect:/usuario/crear";
        }

    }

    @GetMapping("/modificar/{id}")
    public String modificacion(@PathVariable String id, ModelMap modelo) {
        Usuario usu = usuarioservicio.buscarID(id);
        modelo.put("usuario", usu);
        modelo.put("accion", "modificat");
        return "crear_usu";
    }
    
    
    @PostMapping("/modificacion")
    public String modificar(ModelMap modelo,@RequestParam Usuario usuario,String clave){
        Usuario usu=usuarioservicio.buscarID(usuario.getId());
        try{
        usuarioservicio.modificar(usuario, clave);    
        return "inicio.html";    
        }catch(ErrorServicio e){
            modelo.put("mal",e.getMessage());
            modelo.put("usuario",usu);
            return "crear_usu.html";
        }
    }
    
    @GetMapping("/inicio")
    public String inicio(){
        return "inicio.html";
    }
}

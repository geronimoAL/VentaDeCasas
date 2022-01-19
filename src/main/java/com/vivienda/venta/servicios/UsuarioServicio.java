package com.vivienda.venta.servicios;

import com.vivienda.venta.entidades.Usuario;
import com.vivienda.venta.errores.ErrorServicio;
import com.vivienda.venta.repositorio.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioServicio {
    
    @Autowired
    public UsuarioRepository usuariorepositorio;;
    
    //creacion del usuario
      @Transactional
    public void crear(Usuario usuario, String clave) throws ErrorServicio {
        validaciones(usuario.getNombre(),usuario.getApellido(),usuario.getTelefono(), usuario.getClave(), clave);
        Usuario usu = new Usuario();
        usu.setNombre(usuario.getNombre());
        usu.setApellido(usuario.getApellido());
        usu.setTelefono(usuario.getTelefono());
        usu.setClave(usuario.getClave());
        usu.setEstado(true);
        usuariorepositorio.save(usu);
        
    }
    //modificacion del usuario
      @Transactional
     public void modificar(Usuario usuario, String clave) throws ErrorServicio {
         Usuario usu = usuariorepositorio.findById(usuario.getId()).get();
        validaciones(usuario.getNombre(),usuario.getApellido(),usuario.getTelefono(), usuario.getClave(), clave);
        usu.setNombre(usuario.getNombre());
        usu.setApellido(usuario.getApellido());
         usu.setTelefono(usuario.getTelefono());
        usu.setEstado(true);
        usu.setClave(usuario.getClave());
        usuariorepositorio.save(usu);
        
    }
    //eliminar usuario
       @Transactional
     public void eliminar(String id){
         Usuario usu=usuariorepositorio.findById(id).get();
         usuariorepositorio.delete(usu);
         
     }
     //buscar el usuario x su id
       @Transactional(readOnly = true)
     public Usuario buscarID(String id){
         Usuario usuario=usuariorepositorio.findById(id).get();
         return usuario;
     }
     
     //metdodo para que no lleguen vacios, nulo o error de tipeo 
    public void validaciones(String nombre, String apellido, String telefono, String clave, String clave2) throws ErrorServicio {
        if (nombre.isEmpty() || nombre == null) {
            throw new ErrorServicio("Debe ingresar un nombre");
        }
        if (apellido.isEmpty() || apellido == null) {
            throw new ErrorServicio("Debe ingresar un nombre");
        }
        if (telefono.isEmpty() || telefono == null) {
            throw new ErrorServicio("El telefono es incorrecto");
        }
        if (clave.length() < 6) {
            throw new ErrorServicio("La clave no puede tener menos de 6 digitos");
        }
        if (!clave.equals(clave2)) {
            throw new ErrorServicio("Las claves deben ser iguales");
        }
        
    }
    
}

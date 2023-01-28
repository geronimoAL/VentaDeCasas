package com.vivienda.venta.service.impl;

import com.vivienda.venta.domain.Rol;
import com.vivienda.venta.domain.Usuario;
import com.vivienda.venta.enums.role;
import com.vivienda.venta.errors.ErrorServicio;
import com.vivienda.venta.repository.RolRepository;
import com.vivienda.venta.repository.UsuarioRepository;
import com.vivienda.venta.service.EmailService;
import com.vivienda.venta.service.UsuarioServicio;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import javax.mail.MessagingException;
import javax.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Slf4j
@Service()
public class UsuarioServicioImpl implements UsuarioServicio, UserDetailsService {

    @Autowired
    public UsuarioRepository usuariorepositorio;
    @Autowired
    public EmailService emailService;
    @Autowired
    public RolRepository rolRepository;

    //creacion del usuario
    @Transactional
    public void crear(Usuario usuario, String clave) throws ErrorServicio {
        validaciones(usuario.getNombre(), usuario.getApellido(), usuario.getTelefono(), usuario.getClave(), clave);
        Usuario usu = new Usuario();
        usu.setNombre(usuario.getNombre());
        usu.setApellido(usuario.getApellido());
        usu.setTelefono(usuario.getTelefono());
        usu.setCorreo(usuario.getCorreo());
        Rol rol= rolRepository.buscarPorNombre("USER");
        usu.addRole(rol);
        String claveEcriptada = new BCryptPasswordEncoder().encode(usuario.getClave());
        usu.setClave(claveEcriptada);
        usu.setEstado(true);
        try {
            emailService.enviar(usuario.getCorreo(), usuario.getNombre());
        } catch (MessagingException ex) {
            log.error("Error al enviar email al usuario ");
        }
        usuariorepositorio.save(usu);
    }

    //modificacion del usuario
    @Transactional
    public void modificar(Usuario usuario, String clave) throws ErrorServicio {
        Usuario usuarioBuscado = usuariorepositorio.findById(usuario.getId()).get();
        if (usuarioBuscado != null) {
            validaciones(usuario.getNombre(), usuario.getApellido(), usuario.getTelefono(), usuario.getClave(), clave);
            usuarioBuscado.setNombre(usuario.getNombre());
            usuarioBuscado.setApellido(usuario.getApellido());
            usuarioBuscado.setTelefono(usuario.getTelefono());
            usuarioBuscado.setCorreo(usuario.getCorreo());
            usuarioBuscado.setEstado(true);
            String claveEcriptada = new BCryptPasswordEncoder().encode(usuario.getClave());
            usuarioBuscado.setClave(claveEcriptada);
            usuariorepositorio.save(usuarioBuscado);
//
        } else {
            log.error("No se encontro el id {} oara modificar", usuario.getId());
            throw new ErrorServicio("No se encontro el id del usuario para modificar");
        }

    }

    //eliminar usuario
    @Transactional
    public void eliminar(String id) {
        Usuario usu = usuariorepositorio.findById(id).get();
        usuariorepositorio.delete(usu);

    }
    //buscar el usuario x su id

//    @Transactional(readOnly = true)
    public Usuario buscarID(String id) throws ErrorServicio {
        Optional<Usuario> respuesta = usuariorepositorio.findById(id);
        if (respuesta.isPresent()) {
            Usuario usuario = respuesta.get();
            return usuario;
        } else {
            log.error("No se encontro el usuario ");
            throw new ErrorServicio("No se encontró el usuario solicitado");
        }

    }
    
       @Transactional
    public void cambiarRol(Usuario usuario)  {
        Usuario respuesta = usuariorepositorio.findById(usuario.getId()).get();
        respuesta.setRoles(usuario.getRoles());
    }

    //metdodo para que no lleguen vacios, nulo o error de tipeo 
    public void validaciones(String nombre, String apellido, String telefono, String clave, String clave2) throws ErrorServicio {
        if (nombre.isEmpty() || nombre == null) {
            log.error("Error!! Campo nombre en blanco o nulo");
            throw new ErrorServicio("Debe ingresar un nombre");
        }
        if (apellido.isEmpty() || apellido == null) {
            log.error("Error!! Campo apellido en blanco o nulo");
            throw new ErrorServicio("Debe ingresar un apellido");
        }
        if (telefono.isEmpty() || telefono == null) {
            log.error("Error!! Campo teléfono en blanco o nulo");
            throw new ErrorServicio("El telefono es incorrecto");
        }
        if (clave.length() < 6) {
            log.error("Clave ingresada con menos de 6 caracteres");
            throw new ErrorServicio("La clave no puede tener menos de 6 digitos");
        }
        if (!clave.equals(clave2)) {
            log.error("Error!! No coinciden las claves");
            throw new ErrorServicio("Las claves deben ser iguales");
        }

    }

    public void validacionClaves(String clave, String clave2) throws ErrorServicio {
        if (!clave.equals(clave2)) {
            throw new ErrorServicio("Las claves que ingresaste no coinciden"); 
        }
    }

    public String validacionNombre(String nombre) throws ErrorServicio {
        if (nombre.isEmpty() || nombre == null) {
//            log.error("Error!! Campo nombre en blanco o nulo");
            throw new ErrorServicio("Debe ingresar un nombre");
        }
        return "hola";
    }
     public List<Usuario> lista() {
         List<Usuario>lista=usuariorepositorio.findAll();
         return lista;
    }

    @Override
    public UserDetails loadUserByUsername(String mail) throws UsernameNotFoundException {
        Set<Rol> roles ;
        Usuario usuario = usuariorepositorio.buscarXMail(mail);
        if (usuario != null) {
            roles = usuario.getRoles();
            List<GrantedAuthority> permisos = new ArrayList<>();
            //creo una lista de permisos            
//            GrantedAuthority p1 = new SimpleGrantedAuthority("ROLE_USUARIO" + usuario.get());
//            permisos.add(p1);
            for (Rol role : roles) {
                permisos.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
            }

            //Esto me permite guardar el OBJETO USUARIO LOG, para luego ser utilizado
            ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
            HttpSession session = attr.getRequest().getSession(true);
            session.setAttribute("usuariosession", usuario);
            User user = new User(usuario.getCorreo(), usuario.getClave(), permisos);
            return user;
        } else {
            return null;
        }
    }

}

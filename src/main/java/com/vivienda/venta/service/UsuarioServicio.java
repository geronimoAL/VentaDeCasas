package com.vivienda.venta.service;

import com.vivienda.venta.domain.Usuario;
import com.vivienda.venta.errors.ErrorServicio;
import org.springframework.stereotype.Service;


@Service
public interface UsuarioServicio {
    public void crear(Usuario usuario, String clave) throws ErrorServicio;

    public void modificar(Usuario usuario, String clave) throws ErrorServicio;

    public void eliminar(String id);

    public Usuario buscarID(String id) throws ErrorServicio;
    
    public void validaciones(String nombre, String apellido, String telefono, String clave, String clave2) throws ErrorServicio;
      
    public void cambiarRol(Usuario usuario);
    
}

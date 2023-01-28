package com.vivienda.venta.service;

import com.vivienda.venta.domain.Inmobiliaria;
import com.vivienda.venta.errors.ErrorServicio;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

public interface InmobiliariaServicio {

    public void crear(Inmobiliaria inmo, MultipartFile foto) throws ErrorServicio;

    public void modificacion(Inmobiliaria inmo, MultipartFile foto) throws ErrorServicio;

    public void eliminar(String id) throws ErrorServicio;

    public void deshabiliar(String id) throws ErrorServicio;

    public void habilitar(String id) throws ErrorServicio;

    public Inmobiliaria buscarXID(String id);

    public List<Inmobiliaria> listaDeInmobiliarias();

    public void validacion(String nombre, MultipartFile foto) throws ErrorServicio;

}

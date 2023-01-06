package com.vivienda.venta.service;

import com.vivienda.venta.domain.Foto;
import com.vivienda.venta.domain.Vivienda;
import com.vivienda.venta.errors.ErrorServicio;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

public interface ViviendaServicio {

    public void crear(Vivienda vivienda, MultipartFile foto, MultipartFile foto1, MultipartFile foto2, MultipartFile foto3, String idUsu) throws ErrorServicio;

    public void modificar(Vivienda vivienda, MultipartFile foto, MultipartFile foto1, MultipartFile foto2, MultipartFile foto3) throws ErrorServicio;

    public List<Vivienda> ListaDeViviendas();
 
    public List<Vivienda> ListaDeViviendasXUsuario(String id);
        
    public Vivienda BuscarXId(String id) throws ErrorServicio;

    public void eliminar(String id);

    public List<Vivienda> filtrado(String precio, String banio, String cochera, String dormitorio, String mt, String ambiente, String barrio, String ubicacion, String provincia, String inmobiliaria);

    public List<Vivienda> busquedaPrecioMayor(String precio);

    public List<Vivienda> busquedaPrecioMenor(String precio);

    public List<Vivienda> busquedaPorInmobiliaria(String inmobiliaria);

    public List<Vivienda> busquedaPorProvincia(String provincia);

    public void validaciones(String ubicacion, String barrio, String precio, String banio, String dormitorios, String mt, MultipartFile foto, MultipartFile foto1, MultipartFile foto2, MultipartFile foto3) throws ErrorServicio;

}

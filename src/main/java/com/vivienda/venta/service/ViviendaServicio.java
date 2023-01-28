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

    public List<Vivienda> filtrado(long precio, long banio, long cochera, long dormitorio, long mt, long ambiente, String barrio, String ubicacion, String provincia, String inmobiliaria);

    public List<Vivienda> busquedaPrecioMayor(long precio);

    public List<Vivienda> busquedaPrecioMenor(long precio);

    public List<Vivienda> busquedaPorInmobiliaria(String inmobiliaria);

    public List<Vivienda> busquedaPorProvincia(String provincia);
    
    public void validacionNumeros(Long cochera,Long ambiente,Long precio, Long banio, Long dormitorios, Long mt)throws ErrorServicio;
    
    public void validaciones(String ubicacion, String barrio, Long cochera,Long ambiente,Long precio, Long banio, Long dormitorios, Long mt, MultipartFile foto, MultipartFile foto1, MultipartFile foto2, MultipartFile foto3) throws ErrorServicio;

}

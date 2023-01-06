package com.vivienda.venta.service;

import com.vivienda.venta.domain.Foto;
import com.vivienda.venta.domain.Usuario;
import com.vivienda.venta.domain.Vivienda;
import com.vivienda.venta.errors.ErrorServicio;
import com.vivienda.venta.repository.InmobiliariaRepository;
import com.vivienda.venta.repository.ViviendaRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;


@Service
public class ViviendaServicioImpl implements ViviendaServicio{
  @Autowired
    public ViviendaRepository viviendarepository;

    @Autowired
    public InmobiliariaRepository inmobiliariarepository;

    @Autowired
    public FotoServicio fotoservicio;
    @Autowired
    public UsuarioServicioImpl usuarioServicioImpl;
//creacion de la vivienda

    @Transactional
    public void crear(Vivienda vivienda, MultipartFile foto, MultipartFile foto1, MultipartFile foto2, MultipartFile foto3,String idUsu) throws ErrorServicio {
        validaciones(vivienda.getUbicacion(), vivienda.getBarrio(), vivienda.getPrecio(), vivienda.getBanio(), vivienda.getDormitorio(), vivienda.getMt(), foto,foto1,foto2,foto3);
        Vivienda vivi = new Vivienda();
        vivi.setUbicacion(vivienda.getUbicacion());
        vivi.setBarrio(vivienda.getBarrio());
        vivi.setAmbiente(vivienda.getAmbiente());
        vivi.setMt(vivienda.getMt());
        vivi.setCochera(vivienda.getCochera());
        vivi.setDormitorio(vivienda.getDormitorio());
        vivi.setPrecio(vivienda.getPrecio());
        vivi.setBanio(vivienda.getBanio());
        vivi.setInmobiliaria(vivienda.getInmobiliaria());
        vivi.setProvincia(vivienda.getProvincia());
        Usuario usuario=usuarioServicioImpl.buscarID(idUsu);
        vivi.setUsuario(usuario);
        List<Foto> fo = new ArrayList();
        Foto fot = fotoservicio.guardar(foto);
        Foto fot1 = fotoservicio.guardar(foto1);
        Foto fot2 = fotoservicio.guardar(foto2);
        Foto fot3 = fotoservicio.guardar(foto3);
        fo.add(fot);
        fo.add(fot1);
        fo.add(fot2);
        fo.add(fot3);
        vivi.setFoto(fo);
        viviendarepository.save(vivi);

    }
//modificar la vivienda

    @Transactional
    public void modificar(Vivienda vivienda, MultipartFile foto, MultipartFile foto1, MultipartFile foto2, MultipartFile foto3) throws ErrorServicio {
        Vivienda vivi = viviendarepository.findById(vivienda.getId()).get();
        validaciones(vivienda.getUbicacion(), vivienda.getBarrio(), vivienda.getPrecio(), vivienda.getBanio(), vivienda.getDormitorio(), vivienda.getMt(), foto,foto1,foto2,foto3);
        vivi.setUbicacion(vivienda.getUbicacion());
        vivi.setBarrio(vivienda.getBarrio());
        vivi.setAmbiente(vivienda.getAmbiente());
        vivi.setMt(vivienda.getMt());
        vivi.setCochera(vivienda.getCochera());
        vivi.setDormitorio(vivienda.getDormitorio());
        vivi.setPrecio(vivienda.getPrecio());
        vivi.setBanio(vivienda.getBanio());
        vivi.setInmobiliaria(vivienda.getInmobiliaria());
        vivi.setProvincia(vivienda.getProvincia());
        Foto fot = fotoservicio.modificar(vivienda.getId(), foto);
        Foto fot1 = fotoservicio.modificar(vivienda.getId(), foto1);
        Foto fot2 = fotoservicio.modificar(vivienda.getId(), foto2);
        Foto fot3 = fotoservicio.modificar(vivienda.getId(), foto3);
        List<Foto> fo = new ArrayList();
        fo.add(fot);
        fo.add(fot1);
        fo.add(fot2);
        fo.add(fot3);
        vivi.setFoto(fo);
        viviendarepository.save(vivi);

    }
    //buscar la lista de viviendas

    @Transactional(readOnly = true)
    public List<Vivienda> ListaDeViviendas() {
        List<Vivienda> lista = viviendarepository.listaViviendas();
        return lista;

    }
     //buscar la lista de viviendas x usuario

    @Transactional(readOnly = true)
    public List<Vivienda> ListaDeViviendasXUsuario(String id) {
        List<Vivienda> lista = viviendarepository.casasXUsuario(id);
        return lista;

    }
    
//buscar x id de la vivienda

    @Transactional(readOnly = true)
    public Vivienda BuscarXId(String id) throws ErrorServicio {
        Vivienda vivi = viviendarepository.findById(id).get();
        if (vivi != null) {
            return vivi;
        }else{
         throw new ErrorServicio("No se encontro el id en la base de datos");
        }
    }
//eliminar vivienda

    @Transactional
    public void eliminar(String id) {
        Vivienda vivi = viviendarepository.findById(id).get();
        viviendarepository.delete(vivi);
    }
    //filtrado
    @Transactional(readOnly = true)
     public List<Vivienda> filtrado(String precio,String banio,String cochera, String dormitorio, String mt, String ambiente,String barrio,String ubicacion,String provincia,String inmobiliaria) {
        List<Vivienda> lista = viviendarepository.filtrado(precio, banio, cochera, dormitorio, mt, ambiente, barrio, ubicacion, provincia, inmobiliaria);
        return lista;
    }
//buscar x precio mayor que manda x parametro

    @Transactional(readOnly = true)
    public List<Vivienda> busquedaPrecioMayor(String precio) {
        List<Vivienda> lista = viviendarepository.casasxPrecioMayorA(precio);
        return lista;
    }

    //buscar x precio menor que manda x parametro
    @Transactional(readOnly = true)
    public List<Vivienda> busquedaPrecioMenor(String precio) {
        List<Vivienda> lista = viviendarepository.casasxPrecioMenorA(precio);
        return lista;
    }

    //buscar x la inmobiliaria que pide
    @Transactional(readOnly = true)
    public List<Vivienda> busquedaPorInmobiliaria(String inmobiliaria) {
        List<Vivienda> lista = viviendarepository.listaCasasInmobiliaria(inmobiliaria);
        return lista;
    }

    //buscar x la provinicia que elige
    @Transactional(readOnly = true)
    public List<Vivienda> busquedaPorProvincia(String provincia) {
        List<Vivienda> lista = viviendarepository.casasXProvincia(provincia);
        return lista;
    }
    //metdodo para que no lleguen vacios, nulo o error de tipeo 

    public void validaciones(String ubicacion, String barrio, String precio, String banio, String dormitorios, String mt, MultipartFile foto, MultipartFile foto1, MultipartFile foto2, MultipartFile foto3) throws ErrorServicio {

        if (precio.isEmpty() || precio == null) {
            throw new ErrorServicio("No puso ningun precio");
        }
        if (banio.isEmpty() || banio == null) {
            throw new ErrorServicio("No puede ingresar que no tiene baño ");
        }
        if (dormitorios.isEmpty() || dormitorios == null) {
            throw new ErrorServicio("No puede ingresar que no tiene dormitorio ");
        }
        if (mt.isEmpty() || mt == null) {
            throw new ErrorServicio("No puede dejar el espacio en blanco en m²");
        }
        if (ubicacion.isEmpty() || ubicacion == null) {
            throw new ErrorServicio("Debe ingresar una ubicacion");
        }
        if (barrio.isEmpty() || barrio == null) {
            throw new ErrorServicio("Debe ingresar un barrio");
        }
        if (foto.isEmpty() || foto == null) {
            throw new ErrorServicio("Debe ingresar SI O SI fotos");
        }
        if (foto1.isEmpty() || foto == null) {
            throw new ErrorServicio("Debe ingresar SI O SI fotos");
        }
        if (foto2.isEmpty() || foto == null) {
            throw new ErrorServicio("Debe ingresar SI O SI fotos");
        }
        if (foto3.isEmpty() || foto == null) {
            throw new ErrorServicio("Debe ingresar SI O SI fotos");
        }
    }

    
}

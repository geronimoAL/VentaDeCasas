package com.vivienda.venta.servicios;

import com.vivienda.venta.entidades.Foto;
import com.vivienda.venta.entidades.Inmobiliaria;
import com.vivienda.venta.errores.ErrorServicio;
import com.vivienda.venta.repositorio.InmobiliariaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
public class InmobiliariaServicio {

    @Autowired
    public InmobiliariaRepository inmobiliariarepositorio;
    @Autowired
    public FotoServicio fotoservicio;

    //creacion de la inmobiliaria
    @Transactional
    public void crear(Inmobiliaria inmo, MultipartFile foto) throws ErrorServicio {
        validacion(inmo.getNombre(), foto);
        Inmobiliaria inmob = new Inmobiliaria();
        inmob.setNombre(inmo.getNombre());
        Foto fot = fotoservicio.guardar(foto);
        inmob.setFoto(fot);
        inmob.setEstado(true);
        inmobiliariarepositorio.save(inmob);
    }

    //modificacion de la inmobiliaria
    @Transactional
    public void modificacion(String id, String nombre, MultipartFile foto) throws ErrorServicio {
        Inmobiliaria inmo = inmobiliariarepositorio.findById(id).get();
        validacion(nombre, foto);
        inmo.setNombre(nombre);
        Foto fot = fotoservicio.modificar(id, foto);
        inmo.setFoto(fot);
        inmo.setEstado(true);
        inmobiliariarepositorio.save(inmo);
    }

    //eliminar inmobiliaria
    @Transactional
    public void eliminar(String id) throws ErrorServicio {
        Inmobiliaria inmo = inmobiliariarepositorio.findById(id).get();
        if (inmo != null) {
            inmobiliariarepositorio.delete(inmo);
        } else {
            throw new ErrorServicio("No se pudo encontrar la inmobiliaria que querias eliminar");
        }

    }

    //deshabilitar la inmobiliaria.No es borrarla sino que se quedara en la base de datos
    @Transactional
    public void deshabiliar(String id) throws ErrorServicio {
        Inmobiliaria inmo = inmobiliariarepositorio.findById(id).get();
        if (inmo != null) {
            inmo.setEstado(false);
            inmobiliariarepositorio.save(inmo);
        } else {
            throw new ErrorServicio("No se encontro la inmobiliaria que querias desabilitar");
        }
    }

    //habilitar la inmobiliaria.No es borrarla sino que se quedara en la base de datos
    @Transactional
    public void habilitar(String id) throws ErrorServicio {
        Inmobiliaria inmo = inmobiliariarepositorio.findById(id).get();
        if (inmo != null) {
            inmo.setEstado(true);
            inmobiliariarepositorio.save(inmo);
        } else {
            throw new ErrorServicio("No se encontro la inmobiliaria que querias desabilitar");
        }
    }

    //me trae una inmobiliaria x el id que me llega x parametro
    public Inmobiliaria buscarXID(String id) {
        Inmobiliaria inmo = inmobiliariarepositorio.findById(id).get();
        return inmo;
    }

    //metodo para traer todas las inmobiliarias
    @Transactional(readOnly = true)
    public List<Inmobiliaria> listaDeInmobiliarias() {
        List<Inmobiliaria> lista = inmobiliariarepositorio.lista();
        return lista;
    }

    public void validacion(String nombre, MultipartFile foto) throws ErrorServicio {
        if (nombre.isEmpty() || nombre == null) {
            throw new ErrorServicio("Se olvidó de introducir el nombre de su inmobiliaria");
        }
        if (foto.isEmpty() || foto == null) {
            throw new ErrorServicio("Debe cargar una foto si o si");
        }

    }

}

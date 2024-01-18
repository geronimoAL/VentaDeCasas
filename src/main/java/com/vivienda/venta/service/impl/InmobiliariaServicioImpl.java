package com.vivienda.venta.service.impl;

import com.vivienda.venta.domain.Foto;
import com.vivienda.venta.domain.Inmobiliaria;
import com.vivienda.venta.errors.ErrorServicio;
import com.vivienda.venta.repository.InmobiliariaRepository;
import com.vivienda.venta.service.FotoServicio;
import com.vivienda.venta.service.InmobiliariaServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
public class InmobiliariaServicioImpl implements InmobiliariaServicio {

    @Autowired
    public InmobiliariaRepository inmobiliariarepositorio;
    @Autowired
    public FotoServicio fotoservicio;

    @Transactional
    public void crear(Inmobiliaria inmo, MultipartFile foto) throws ErrorServicio {
        validacion(inmo.getNombre(), foto);
        if (buscarPorNombre(inmo.getNombre())) {
            throw new ErrorServicio("El nombre ingresado ya está en la base de datos");
        }
        Foto fot = fotoservicio.guardar(foto);
        Inmobiliaria inmob = Inmobiliaria.builder()
                .nombre(inmo.getNombre())
                .foto(fot)
                .estado(true)
                .build();
        inmobiliariarepositorio.save(inmob);
    }

    @Transactional
    public void modificacion(Inmobiliaria inmo, MultipartFile foto) throws ErrorServicio {
        if (buscarPorNombre(inmo.getNombre())) {
            throw new ErrorServicio("El nombre ingresado ya está en la base de datos");
        }
        Inmobiliaria inmobiliariaBuscada = inmobiliariarepositorio.getById(inmo.getId());
        validacion(inmo.getNombre(), foto);
        inmobiliariaBuscada.setNombre(inmo.getNombre());
        Foto fot = fotoservicio.modificar(inmo.getId(), foto);
        inmobiliariaBuscada.setFoto(fot);
        inmobiliariaBuscada.setEstado(true);
        inmobiliariarepositorio.save(inmobiliariaBuscada);

    }

    @Transactional
    public void eliminar(String id) throws ErrorServicio {
        if (id != null) {
            inmobiliariarepositorio.deleteById(id);
        } else {
            log.error("El id {} no se encontró para eliminar",id);
            throw new ErrorServicio("No se pudo encontrar la inmobiliaria que querias eliminar");
        }

    }

    @Transactional
    public void deshabiliar(String id) throws ErrorServicio {
        Inmobiliaria inmo = inmobiliariarepositorio.findById(id).get();
        if (inmo != null) {
            inmo.setEstado(false);
            inmobiliariarepositorio.save(inmo);
        } else {
            log.error("El id {} no se encontró para deshabilitar",id);
            throw new ErrorServicio("No se encontro la inmobiliaria que querias desabilitar");
        }
    }

    @Transactional
    public void habilitar(String id) throws ErrorServicio {
        Inmobiliaria inmo = inmobiliariarepositorio.findById(id).get();
        if (inmo != null) {
            inmo.setEstado(true);
            inmobiliariarepositorio.save(inmo);
        } else {
            log.error("El id {} no se encontró para Habilitar",id);
            throw new ErrorServicio("No se encontro la inmobiliaria que querias habilitar");
         
        }
    }

    public Inmobiliaria buscarXID(String id) {
        Inmobiliaria inmo = inmobiliariarepositorio.findById(id).get();
        return inmo;
    }

    @Transactional(readOnly = true)
    public List<Inmobiliaria> listaDeInmobiliarias() {
        List<Inmobiliaria> lista = inmobiliariarepositorio.lista();
        if (lista.isEmpty()){
            log.info("No hay inmobiliarias en la base de datos");
        }
        return lista;
    }
    
    public void validacion(String nombre, MultipartFile foto) throws ErrorServicio {
        if (nombre.isEmpty() || nombre == null) {
            throw new ErrorServicio("Se olvidó de introducir el nombre de su inmobiliaria");
        }
        if (foto.isEmpty() || foto == null) {
            log.error("Hay foto de la inmobiliaria {} vacia",nombre);
            throw new ErrorServicio("Debe cargar una foto si o si");
        }

    }
    public boolean buscarPorNombre(String nombre){
        Inmobiliaria provinciaExiste=inmobiliariarepositorio.nombreInmobiliaria(nombre);
        if (provinciaExiste !=null ) {
            return true;
        }else{
            return false;
        }
    }

}

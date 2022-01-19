
package com.vivienda.venta.controlador;

import com.vivienda.venta.entidades.Inmobiliaria;
import com.vivienda.venta.entidades.Vivienda;
import com.vivienda.venta.errores.ErrorServicio;
import com.vivienda.venta.servicios.InmobiliariaServicio;
import com.vivienda.venta.servicios.ViviendaServicio;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/foto")
public class FotoControlador {
    
    @Autowired
    public ViviendaServicio viviendaservicio;
    @Autowired
    public InmobiliariaServicio inmobiliariaservicio;
    
    @GetMapping("/vivienda")
    public ResponseEntity<byte[]>fotoequipo(@RequestParam String id,@RequestParam Integer num) {
        try {
            Vivienda vivienda=viviendaservicio.BuscarXId(id);
            if (vivienda.getFoto() ==null) {
                throw new ErrorServicio("No tiene una foto asignada");
            }
            byte[]foto=vivienda.getFoto().get(num).getContenido();
            HttpHeaders headers=new  HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG);
            return new ResponseEntity<>(foto,headers,HttpStatus.OK);
        } catch (ErrorServicio ex) {
            Logger.getLogger(FotoControlador.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/inmobiliaria")
    public ResponseEntity<byte[]>fotoinmobiliaria(@RequestParam String id) throws ErrorServicio{
       Inmobiliaria inmo=inmobiliariaservicio.buscarXID(id);
        if (inmo.getFoto() == null) {
            throw new ErrorServicio("La inmobiliaria no tiene foto asignada");
        }
        byte[]foto=inmo.getFoto().getContenido();
        HttpHeaders headers=new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        return new ResponseEntity<>(foto,headers,HttpStatus.OK);
    }
    
    
}

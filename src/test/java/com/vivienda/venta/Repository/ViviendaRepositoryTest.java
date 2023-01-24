/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vivienda.venta.Repository;

import com.vivienda.venta.domain.Inmobiliaria;
import com.vivienda.venta.domain.Usuario;
import com.vivienda.venta.domain.Vivienda;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import com.vivienda.venta.repository.ViviendaRepository;
import com.vivienda.venta.repository.UsuarioRepository;
import com.vivienda.venta.repository.InmobiliariaRepository;
import java.util.List;
import org.junit.Ignore;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 *
 * @author geron
 */
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@SpringBootTest
public class ViviendaRepositoryTest {

    @Autowired
    private ViviendaRepository viviendaRepository;
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Autowired
    private InmobiliariaRepository inmobiliariaRepository;

    @DisplayName("Test buscar viviendas por provincia")
    @Test
    void testBuscarPorProvincia(){
        //given
        String nombreProvinca="Mendoza";
        //when
        List<Vivienda> nombreViviendasPorProvincia = viviendaRepository.casasXProvincia(nombreProvinca);
        //then
        Assertions.assertEquals(4, nombreViviendasPorProvincia.size());
    }
    @DisplayName("Test buscar viviendas por usuario")
    @Test
    void testBuscarPorUsuario(){
        //given
        Usuario usuarioBuscado=usuarioRepository.buscarXMail("geronimo@gmail");
        //when
        List<Vivienda> nombreViviendasPorProvincia = viviendaRepository.casasXUsuario(usuarioBuscado.getId());
        //then
        Assertions.assertEquals(3, nombreViviendasPorProvincia.size());
    }
    
    @DisplayName("Test buscar viviendas por precio mayor pasado por parámetro")
    @Test
    void testBuscarPorPrecioMayor(){
        //given
        long precio=3000;
        //when
        List<Vivienda> viviendasConPrecioMayorPorParametro = viviendaRepository.casasxPrecioMayorA(precio);
        //then
        Assertions.assertEquals(2, viviendasConPrecioMayorPorParametro.size());
    }
    @DisplayName("Test buscar viviendas por inmobiliaria")
    @Test
    void testBuscarPorInmobiliaria(){
        //given
        String nombreInmobiliaria="Stevanato";
        //when
        List<Vivienda>viviendasConInmobiliariaBuscada = viviendaRepository.listaCasasInmobiliaria(nombreInmobiliaria);
        //then
        Assertions.assertEquals(4, viviendasConInmobiliariaBuscada.size());
    }
    @DisplayName("Test buscar viviendas por filtrado")
    @Test
    void testBuscarPorFiltrado() {
        //given
        String nombreInmobiliaria = "Stevanato";
        String nombreProvincia = "Mendoza";
        String nombreUbicacion = "Godoy Cruz";
        String nombreBarrio = "Malcaya";
        //when
        List<Vivienda> viviendasConInmobiliariaBuscada = viviendaRepository.filtrado(6000, 2, 3, 6, 200, 4, nombreBarrio, nombreUbicacion, nombreProvincia, nombreInmobiliaria);
        //then
        Assertions.assertEquals(3, viviendasConInmobiliariaBuscada.size());
        
    }
    
}

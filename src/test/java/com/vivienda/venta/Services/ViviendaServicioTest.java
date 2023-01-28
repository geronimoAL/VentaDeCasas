/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vivienda.venta.Services;

import com.vivienda.venta.domain.Inmobiliaria;
import com.vivienda.venta.domain.Provincia;
import com.vivienda.venta.domain.Vivienda;
import com.vivienda.venta.errors.ErrorServicio;
import com.vivienda.venta.repository.ViviendaRepository;
import com.vivienda.venta.service.impl.ViviendaServicioImpl;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

/**
 *
 * @author geron
 */
@SpringBootTest
public class ViviendaServicioTest {

//    @Mock
//    private ViviendaRepository viviendaRepository;
//
//    @InjectMocks
//    private ViviendaServicioImpl viviendaServicioImpl;
//
//    private Vivienda vivienda;
//    private Vivienda vivienda2;
//    private Inmobiliaria nuevaInmobiliaria;
//    private Provincia provincia;
//
//    @BeforeEach
//    public void setup() {
//        provincia = Provincia.builder()
//                .nombre("San Juan")
//                .build();
//        nuevaInmobiliaria = Inmobiliaria.builder()
//                .nombre("Remax")
//                .estado(Boolean.TRUE)
//                .build();
//        vivienda = Vivienda.builder()
//                .banio(2L)
//                .cochera(3L)
//                .dormitorio(4L)
//                .mt(200L)
//                .precio(123L)
//                .ambiente(4L)
//                .ubicacion("Gutierrez")
//                .inmobiliaria(nuevaInmobiliaria)
//                .provincia(provincia)
//                .build();
//        vivienda2 = Vivienda.builder()
//                .banio(5L)
//                .cochera(2l)
//                .dormitorio(3L)
//                .mt(300L)
//                .precio(123L)
//                .ambiente(4L)
//                .ubicacion("Luzuriaga")
//                .inmobiliaria(nuevaInmobiliaria)
//                .provincia(provincia)
//                .build();
//    }
//
//    @DisplayName("Filtrado de vivienda")
//    @Test
//    public void filtrado() {
//        BDDMockito.given(viviendaRepository.filtrado(Mockito.anyLong(), Mockito.anyLong(), Mockito.anyLong(), Mockito.anyLong(),
//                Mockito.anyLong(), Mockito.anyLong(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString())).willReturn(Arrays.asList(vivienda,vivienda2));
//
//        List<Vivienda> lista = viviendaServicioImpl.filtrado(2, 3, 4, 5, 2, 2, "Escala", "Maipu", "Mendoza", "Remax");
//        Assertions.assertEquals(2, lista.size());
//    }
//    
//    @DisplayName("Filtrado de inmobiliaria")
//    @Test
//    public void filtradoPorInmobiliaria() {
//        BDDMockito.given(viviendaRepository.listaCasasInmobiliaria("Remax")).willReturn(Arrays.asList(vivienda,vivienda2));
//        List<Vivienda> lista = viviendaServicioImpl.busquedaPorInmobiliaria("Remax");
//        Assertions.assertEquals(2, lista.size());
//    }
//    
//    
//    @DisplayName("Comprobar validacion numero negativo")
//    @Test
//    public void comprobarValidacionNumeroNegativo(){
//        Exception exception= Assertions.assertThrows(ErrorServicio.class, ()->viviendaServicioImpl.validacionNumeros(2L, 2L, 2L, 2L,2L,-1L), ()->"diferente excepcion");
//        String mensajeEsperado = "No ingreso los metros cuadrados de la casa";
//        String actualMensaje = exception.getLocalizedMessage();
//        //then
//        Assertions.assertEquals(mensajeEsperado,actualMensaje);
//    }
//    
//     @DisplayName("Comprobar validacion si es nulo")
//    @Test
//    public void comprobarValidacionSiEsNulo(){
//        Assertions.assertThrows(NullPointerException.class, ()->viviendaServicioImpl.validaciones("Solares", "Pinares", 2L, 2L, 2L, 2L, 2L, 2L, null, null, null, null));
//    }

}

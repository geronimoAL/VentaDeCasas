/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vivienda.venta.Services;

import com.vivienda.venta.domain.Inmobiliaria;
import com.vivienda.venta.errors.ErrorServicio;
import com.vivienda.venta.repository.InmobiliariaRepository;
import com.vivienda.venta.service.impl.InmobiliariaServicioImpl;
import java.util.ArrayList;
import static java.util.Arrays.asList;
import java.util.List;
import java.util.Optional;
import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.times;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 *
 * @author geron
 */
@ExtendWith(MockitoExtension.class)
public class InmobiliariaServicioTest {

    private Inmobiliaria inmobiliaria;
    private Inmobiliaria inmobiliaria2;

    @Mock
    private InmobiliariaRepository inmobiliariaRepository;

    @InjectMocks
    private InmobiliariaServicioImpl inmobiliariaServicioimpl;
    
    
    @BeforeEach
    void setup(){
        inmobiliaria = Inmobiliaria.builder()
                .id("1")
                .nombre("Stevanato")
                .estado(true)
                .build();
        inmobiliaria2 = Inmobiliaria.builder()
                .id("2")
                .nombre("Remax")
                .estado(true)
                .build();
    }
    
    @DisplayName("Test para traer inmobiliarias")
    @Test
    void testListaInmobiliarias(){
        //given
 
        BDDMockito.given(inmobiliariaRepository.lista()).willReturn(asList(inmobiliaria,inmobiliaria2));
        
        //when
        List<Inmobiliaria> listaInmobiliarias = inmobiliariaServicioimpl.listaDeInmobiliarias();

        //then
        Assertions.assertNotNull(listaInmobiliarias);
        Assertions.assertEquals(2,listaInmobiliarias.size());
    }

    @DisplayName("Test para traer inmobiliaria por id")
    @Test
    void testInmobiliariaPorId(){
        //given
 
        BDDMockito.given(inmobiliariaRepository.findById("1L")).willReturn(Optional.of(inmobiliaria));
        
        //when
        Inmobiliaria inmobiliaria = inmobiliariaServicioimpl.buscarXID("1L");

        //then
        Assertions.assertNotNull(inmobiliaria);
    }
    
    @DisplayName("Test para eliminar inmobiliaria")
    @Test
    void testEliminarInmobiliaria() throws ErrorServicio{
        //given
       String id="2L";
        BDDMockito.willDoNothing().given(inmobiliariaRepository).deleteById(id);
        //when
        inmobiliariaServicioimpl.eliminar(id);
        //then
        BDDMockito.verify(inmobiliariaRepository,times(1)).deleteById(id);
    }
}

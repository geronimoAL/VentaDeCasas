/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vivienda.venta.Repository;

import com.vivienda.venta.domain.Inmobiliaria;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import com.vivienda.venta.repository.InmobiliariaRepository;
import org.junit.jupiter.api.Assertions;

/**
 *
 * @author geron
 */
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@SpringBootTest
public class InmobiliariaRepositoryTest {

   @Autowired
   private InmobiliariaRepository inmobiliariaRepository;

   @DisplayName("Test para buscar una inmobiliaria")
   @Test
   void testBuscarPorId() {
       Inmobiliaria inmobiliaria = Inmobiliaria.builder()
               .estado(true)
               .nombre("REMAX")
               .build();
       inmobiliariaRepository.save(inmobiliaria);

       Inmobiliaria inmobiliariaEncontrada = inmobiliariaRepository.findById(inmobiliaria.getId()).get();

       Assertions.assertNotNull(inmobiliariaEncontrada);
   }
}

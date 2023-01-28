/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vivienda.venta.Repository;

import com.vivienda.venta.domain.Usuario;
import com.vivienda.venta.repository.UsuarioRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

/**
 *
 * @author geron
 */
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@SpringBootTest
public class UsuarioRepositoryTest {
   @Autowired
   private UsuarioRepository  usuarioRepository;
   
   @DisplayName("Test para buscar una inmobiliaria")
   @Test
   void testBuscarPorCorreo(){

       Usuario inmobiliariaEncontrada = usuarioRepository.buscarXMail("martin@gmail");

        Assertions.assertNotNull(inmobiliariaEncontrada);
   }
   
}

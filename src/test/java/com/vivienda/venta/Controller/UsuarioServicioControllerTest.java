/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vivienda.venta.Controller;

import com.vivienda.venta.domain.Usuario;
import com.vivienda.venta.enums.role;
import com.vivienda.venta.service.impl.UsuarioServicioImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;



@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class UsuarioServicioControllerTest {
    
   private Usuario usuario;
   
   @Mock
   private UsuarioServicioImpl usuarioServicioImpl;
   
   @BeforeEach
   private void setup(){
       usuario= Usuario.builder()
               .id("1L")
               .nombre("Geronimo")
               .apellido("Luminari")
               .estado(Boolean.TRUE)
               .correo("geronimo@gmail")
//                .rol
               .telefono("212131")
               .clave("123456")
               .build();
   }
   
   @DisplayName("Crear Usuario")
   @Test
   public void crearUsuarioTest() throws Exception{
       
       BDDMockito.doNothing().when(usuarioServicioImpl).crear(usuario, "123456");
       
       usuarioServicioImpl.crear(usuario, "123456");
       
       BDDMockito.verify(usuarioServicioImpl,times(1)).crear(usuario, "123456");
   }
   
   @DisplayName("Modificar Usuario")
   @Test
   public void modificarUsuarioTest() throws Exception{
       
       BDDMockito.doNothing().when(usuarioServicioImpl).modificar(usuario, "123456");
       
       usuarioServicioImpl.modificar(usuario, "123456");
       
       BDDMockito.verify(usuarioServicioImpl,times(1)).modificar(usuario, "123456");
   }
   
}

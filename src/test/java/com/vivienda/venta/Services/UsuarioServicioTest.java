/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vivienda.venta.Services;

import com.vivienda.venta.domain.Usuario;
import com.vivienda.venta.enums.role;
import com.vivienda.venta.errors.ErrorServicio;
import com.vivienda.venta.repository.UsuarioRepository;
import com.vivienda.venta.service.impl.UsuarioServicioImpl;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class UsuarioServicioTest {
//
//    @InjectMocks
//    private UsuarioServicioImpl usuarioServicioImpl;
//
//    @Mock
//    private UsuarioRepository usuarioRepository;
//
//    private Usuario usuario;
//
//    private Usuario usuario2;
//
//    @BeforeEach
//    void setup() {
//        usuario = Usuario.builder()
//                .nombre("gero")
//                .apellido("lumi")
//                .correo("gero@gmail")
//                .estado(Boolean.TRUE)
//                .clave("123456")
//                .telefono("12131231")
////                .rol(role.USUARIO)
//                .build();
//        usuario2 = Usuario.builder()
//                .nombre("german")
//                .apellido("paquio")
//                .correo("paquio@gmail")
//                .estado(Boolean.TRUE)
//                .clave("123456")
//                .telefono("12131231")
////                .rol(role.USUARIO)
//                .build();
//
//    }
//
////    @DisplayName("Crear usuario")
////    @Test
////       void testCrearUsuario() throws ErrorServicio{
////        //given
////        String claveRepetida="123456";
//////        BDDMockito.given(inmobiliariaRepository.lista()).willReturn(asList(inmobiliaria,inmobiliaria2));
////        Usuario usuario=Usuario.builder()
////                .nombre("Geronimo")
////                .apellido("Luminari")
////                .correo("geronimoluminari3@outlook.com")
////                .estado(Boolean.TRUE)
////                .telefono("1312313")
////                .rol(Rol.USUARIO)
////                .clave("123456")
////                .build();
////        //when
////        usuarioServicioImpl.crear(usuario, claveRepetida);
////        //then
////        Assertions.assertEquals(3,usuarioRepository.count());
////    }
//    @DisplayName("Probando excepcion al crear usuario")
//    @Test()
//    void testProbandoExcepcionAlCrearUsuario() {
//        //given
//        String claveRepetida = "123456";
//        Usuario usuario = Usuario.builder()
//                .nombre(null)
//                .apellido("Luminari")
//                .correo("geronimoluminari3@outlook.com")
//                .estado(Boolean.TRUE)
//                .telefono("1312313")
////                .rol(role.USUARIO)
//                .clave("123456")
//                .build();
//        //when
//
//        Exception exception = Assertions.assertThrows(ErrorServicio.class, () -> usuarioServicioImpl.validacionNombre(""), () -> "diferente excepcion");
//        String mensajeEsperado = "Debe ingresar un nombre";
//        String actualMensaje = exception.getLocalizedMessage();
//        //then
//        Assertions.assertEquals(mensajeEsperado, actualMensaje);
//    }
//
////    @DisplayName("Actualizar usuario")
////    @Test
////    public void testActualizarUsuario() throws ErrorServicio{
////        //given
////        String id="1L";
////        String clave="123456";
////        
//////        BDDMockito.given(usuarioRepository.findById("2l")).willReturn(Optional.of(usuario2));
////        BDDMockito.willDoNothing().given(usuarioRepository).save(usuario);
//////                   willDoNothing().given(empleadoRepository).deleteById(empleadoId);
//////        usuario.setCorreo("lumi@gmail.com");
////        //when
//////        usuarioServicioImpl.modificar(usuario, clave);
////        //then
//////        BDDMockito.verify(usuarioRepository,times(1)).save(usuario);
////    }
}

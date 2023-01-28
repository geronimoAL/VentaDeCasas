
package com.vivienda.venta.Controller;


import com.vivienda.venta.domain.Inmobiliaria;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.mockito.BDDMockito;
import static org.mockito.Mockito.times;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.multipart.MultipartFile;
import com.vivienda.venta.service.impl.InmobiliariaServicioImpl;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;


import org.junit.jupiter.api.Test;


@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class InmobiliariaControllerTest {
   
   private MockMvc mockMvc;
   
   @Mock
   private InmobiliariaServicioImpl inmobiliariaServicioImpl;
   
   private Inmobiliaria inmobiliaria;
   
   private MultipartFile foto;
   
   @BeforeEach
   private void setup(){
       inmobiliaria= Inmobiliaria.builder()
               .estado(Boolean.TRUE)
               .nombre("MATRIZ")
               .build();
       
   }
   
   @DisplayName("Crear inmobiliaria")
   @Test
   public void crearInmobiliariaTest() throws Exception{
       //given
       BDDMockito.doNothing().when(inmobiliariaServicioImpl).crear(inmobiliaria, foto);
       //when
       inmobiliariaServicioImpl.crear(inmobiliaria, foto);
       //then
       BDDMockito.verify(inmobiliariaServicioImpl,times(1)).crear(inmobiliaria, foto);
   }
   
   @DisplayName("Modificar inmobiliaria")
   @Test
   public void modificarInmobiliariaTest() throws Exception{
       //given
       BDDMockito.doNothing().when(inmobiliariaServicioImpl).modificacion(inmobiliaria, foto);
       //when
       inmobiliariaServicioImpl.modificacion(inmobiliaria, foto);
       //then
       BDDMockito.verify(inmobiliariaServicioImpl,times(1)).modificacion(inmobiliaria, foto);
   }
   
}

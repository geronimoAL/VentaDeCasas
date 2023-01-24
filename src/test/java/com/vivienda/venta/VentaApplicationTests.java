package com.vivienda.venta;

import com.vivienda.venta.domain.Provincia;
import com.vivienda.venta.repository.ProvinciaRepository;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest()
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class VentaApplicationTests {

    @Autowired
    private ProvinciaRepository provinciaRepository;

    @DisplayName("Test para guardar una provincia")
    @Test
    void testGuardarEmpleado() {
//        //given - dado o condición previa o configuración
        Provincia provincia1 = new Provincia();
        provincia1.setNombre("Yankees");
////
////       //  //when - acción o el comportamiento que vamos a probar
        provinciaRepository.save(provincia1);
        List<Provincia> lista = provinciaRepository.findAll();
//        //then - verificar la salida
        Assertions.assertEquals(9, lista.size());
    }

}

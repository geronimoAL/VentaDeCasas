
package com.vivienda.venta.Repository;



import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import com.vivienda.venta.domain.Provincia;
import com.vivienda.venta.repository.ProvinciaRepository;


@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@SpringBootTest
public class ProvinciaRepositoryTest {

    @Autowired
    private ProvinciaRepository provinciaRepository;
    
    @DisplayName("Test para buscar una provincia")
    @Test
    void testBuscarPorNombre(){
        Provincia provincia1 = provinciaRepository.buscarXNombre("chicago");

         Assertions.assertNotNull(provincia1);
    }
    

    
}

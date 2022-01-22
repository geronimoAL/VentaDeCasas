
package com.vivienda.venta;

import com.vivienda.venta.servicios.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;


public class ServletInitializer extends SpringBootServletInitializer{
    
    @Autowired
    private UsuarioServicio usuarioServicio;
    
    @Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
           return application.sources(VentaApplication.class);
        }
    
}

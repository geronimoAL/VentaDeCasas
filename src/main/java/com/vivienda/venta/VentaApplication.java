package com.vivienda.venta;

import com.vivienda.venta.service.UsuarioServicioImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;




@SpringBootApplication
public class VentaApplication {

    @Autowired
    private UsuarioServicioImpl usuarioServicioImpl;

    public static void main(String[] args) {
        SpringApplication.run(VentaApplication.class, args);
    }
 
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(usuarioServicioImpl)
                .passwordEncoder(new BCryptPasswordEncoder());

    }
}

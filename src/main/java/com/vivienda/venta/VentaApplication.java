package com.vivienda.venta;

import com.vivienda.venta.service.impl.UsuarioServicioImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;




@SpringBootApplication
@EnableAsync
public class VentaApplication {

    @Autowired
    private UsuarioServicioImpl usuaServicioImpl;

    public static void main(String[] args) {
        SpringApplication.run(VentaApplication.class, args);
    }
 
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(usuaServicioImpl)
                .passwordEncoder(new BCryptPasswordEncoder());

    }
}

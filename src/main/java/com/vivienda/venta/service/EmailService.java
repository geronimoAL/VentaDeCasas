package com.vivienda.venta.service;

import java.io.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.context.Context;
import org.springframework.core.io.FileSystemResource;

@Service
public class EmailService {
    
    @Autowired
    private JavaMailSender sender;

    // @Value("${spring.mail.username}")
    // private String from;

    private static final String SUBJECT = "Saludo";

  
    
    @Async
    public void enviar(String to, String nombre) throws MessagingException {
        //SimpleMailMessage message=new SimpleMailMessage();
        MimeMessage mimeMessage;mimeMessage = sender.createMimeMessage();
        MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
        Context context = new Context();
        message.setTo(to);
        message.setFrom(to);
        message.setSubject(SUBJECT);
        String textoEmail = String.format("<b>Bienvenido %s !!!<b>,<br><i>Gracias por registrarte en nuestra aplicación !!!</i>"
                + "<br><img src='cid:image001'/><br><b>Saludos!!!</b> ", nombre);
        message.setText(textoEmail, true);
        FileSystemResource resource = new FileSystemResource(new File("C:\\Users\\geron\\Downloads\\CURSO EGG\\fotos casas\\selerahogar.png"));
        message.addInline("image001", resource);
        sender.send(mimeMessage);
    }

}

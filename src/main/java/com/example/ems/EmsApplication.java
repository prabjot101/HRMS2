package com.example.ems;

import com.example.ems.Controller.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.mail.MessagingException;

@SpringBootApplication
public class EmsApplication implements WebMvcConfigurer {

    @Autowired
    private EmailSenderService senderService;

    public void addViewController(ViewControllerRegistry registry)
    {
        registry.addViewController("/dashboard").setViewName("dashboard");
    }

    @EventListener(ApplicationReadyEvent.class)
    public void triggerMail() throws MessagingException {

        senderService.sendSimpleEmail("rajat.kadian123@gmail.com",
                "Your Salary Slip is generated and Salary is Credited!! Good Day ",
                "Salary Credited");

    }

    public static void main(String[] args) {
        SpringApplication.run(EmsApplication.class, args);
    }

}



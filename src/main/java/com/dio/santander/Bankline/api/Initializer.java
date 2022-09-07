package com.dio.santander.Bankline.api;

import com.dio.santander.Bankline.api.Entities.Corretista;
import com.dio.santander.Bankline.api.repository.CorretistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Optional;

@Component
public class Initializer implements CommandLineRunner {
    @Autowired
    private CorretistaRepository corretistaRepository;

    @Override
    public void run(String... args) throws Exception {
       Optional<Corretista> corretista = corretistaRepository.findByUsername("admin");


        corretistaRepository.save(Corretista.builder()
                .nome("Jãoa")
                .cfp("129.34563.3874")
                .username("admin")
                .password("admin123")
                .roles(new ArrayList<String>(){{add("ADMIN");}})
                .build());



    }
}

package com.dio.santander.Bankline.api.Controllers;

import com.dio.santander.Bankline.api.Entities.Corretista;
import com.dio.santander.Bankline.api.Services.CorrentistaService;
import com.dio.santander.Bankline.api.repository.CorretistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/correntista")
public class CorretistaController {

    @Autowired
    private CorretistaRepository corretistaRepository;

    @Autowired
    private CorrentistaService service;

    @GetMapping
    public List<Corretista> coretista(){
        return corretistaRepository.findAll();
    }

    @GetMapping("/{idConta}")
    public Corretista findByIdConta(@PathVariable Long idConta){
        return corretistaRepository.findById(idConta).get();
    }

    @PostMapping
    public void novoCorrentista(@RequestBody Corretista correntista){
        service.save(correntista);
    }

}

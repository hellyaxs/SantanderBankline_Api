package com.dio.santander.Bankline.api.Controllers;

import com.dio.santander.Bankline.api.Entities.Corretista;
import com.dio.santander.Bankline.api.Services.CorrentistaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user/correntista")
public class CorretistaController {


    @Autowired
    private CorrentistaService service;

    @GetMapping
    //@PreAuthorize("hasAnyRole('ADMIN')")
    public List<Corretista> coretista(){
        return service.findAll();
    }

    @GetMapping("/{idConta}")
    public Corretista findByIdConta(@PathVariable Long idConta) throws Throwable {
        return service.findByIdConta(idConta);
    }

    @PostMapping
    public void novoCorrentista(@RequestBody Corretista correntista){
        service.save(correntista);
    }

}

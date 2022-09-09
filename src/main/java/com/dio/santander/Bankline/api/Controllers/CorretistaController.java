package com.dio.santander.Bankline.api.Controllers;

import com.dio.santander.Bankline.api.Entities.Corretista;
import com.dio.santander.Bankline.api.Services.CorrentistaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users/correntista")
public class CorretistaController {

    @Autowired
    private CorrentistaService service;

    @GetMapping
    //@PreAuthorize("hasAnyRole('ADMIN')")
    public Page<Corretista> coretista(@RequestParam(defaultValue = "0") int page,
                                      @RequestParam(defaultValue = "3") int size,
                                      @SortDefault(sort="username",direction= Sort.Direction.ASC) Sort sort){
        Pageable pageable = PageRequest.of(page,size,sort);
        return service.findAll(pageable);
    }

    @GetMapping("/{idConta}")
    public Corretista findByIdConta(@PathVariable Long idConta) throws Throwable {
        return service.findByIdConta(idConta);
    }

    @PostMapping
    public void novoCorrentista(@RequestBody Corretista correntista){
        service.save(correntista);
    }

    @DeleteMapping("/{idConta}")
    @ResponseStatus(HttpStatus.GONE)
    public void removeCorrentista(@PathVariable Long idConta){
        service.deleteById(idConta);
    }



}

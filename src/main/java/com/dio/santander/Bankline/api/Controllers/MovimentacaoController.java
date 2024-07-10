package com.dio.santander.Bankline.api.Controllers;

import com.dio.santander.Bankline.api.DTO.MovimentacaoDTO;
import com.dio.santander.Bankline.api.Entities.Movimentacao;
import com.dio.santander.Bankline.api.Services.MovimentcaoService;
import com.dio.santander.Bankline.api.repository.MovimentacaoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("users/movimentacoes")
public class MovimentacaoController {

    @Autowired
    private MovimentcaoService movimentcaoService;
    @Autowired
    private MovimentacaoRepository movimentacaoRepository;

    @GetMapping
    public List<Movimentacao> listartodos(){
        return movimentacaoRepository.findAll();
    }

    @GetMapping("/{idConta}")
    public List<Movimentacao> findAll(@PathVariable("idConta") Long idConta){
        return movimentcaoService.findByIdConta(idConta);
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody @Valid MovimentacaoDTO novaMovimentacao){
        movimentcaoService.save(novaMovimentacao);
    }
}

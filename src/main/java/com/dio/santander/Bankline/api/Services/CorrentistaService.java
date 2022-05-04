package com.dio.santander.Bankline.api.Services;

import com.dio.santander.Bankline.api.Entities.Conta;
import com.dio.santander.Bankline.api.Entities.Corretista;
import com.dio.santander.Bankline.api.repository.CorretistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CorrentistaService {
    @Autowired
    private CorretistaRepository repository;

    public void save(Corretista correntista){
        Corretista novoCorrentista = new Corretista();
        novoCorrentista.setNome(correntista.getNome());
        novoCorrentista.setCfp(correntista.getCfp());

        Conta conta = new Conta();
        conta.setSaldo(0.0);
        conta.setNumero(new Date().getTime());

        novoCorrentista.setConta(conta);
        repository.save(novoCorrentista);
    }
}

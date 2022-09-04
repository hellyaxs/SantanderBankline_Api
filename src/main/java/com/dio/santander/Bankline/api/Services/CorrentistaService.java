package com.dio.santander.Bankline.api.Services;

import com.dio.santander.Bankline.api.Entities.Conta;
import com.dio.santander.Bankline.api.Entities.Corretista;
import com.dio.santander.Bankline.api.Exceptions.CorrentistaException;
import com.dio.santander.Bankline.api.repository.CorretistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CorrentistaService {
    @Autowired
    private CorretistaRepository repository;

    public void save(Corretista correntista) {

        Conta conta = new Conta();
        conta.setSaldo(0.0);
        conta.setNumero(new Date().getTime());

        correntista.setConta(conta);
        repository.save(correntista);
    }

    public Page<Corretista> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Corretista findByIdConta(Long id) throws CorrentistaException {
        return repository.findById(id).orElseThrow(() -> new CorrentistaException("NOT FOUND corretistas with id: " + id));
    }
}

package com.dio.santander.Bankline.api.Services;

import com.dio.santander.Bankline.api.Entities.Conta;
import com.dio.santander.Bankline.api.Entities.Corretista;
import com.dio.santander.Bankline.api.Entities.TipoConta;
import com.dio.santander.Bankline.api.Exceptions.CorrentistaException;
import com.dio.santander.Bankline.api.repository.CorretistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.*;
import java.util.Date;
import java.util.List;

@Service
public class CorrentistaService {
    @Autowired
    private CorretistaRepository repository;


    @Transactional
    public void save(Corretista correntista) {

        Conta conta = new Conta();
        conta.setSaldo(0.0);
        conta.setNumero(new Date().getTime());
        conta.setTipoConta(TipoConta.CORRENTE);
        var passwordEncoder = new BCryptPasswordEncoder();
        correntista.setPassword(passwordEncoder.encode(correntista.getPassword()));//mascarando senha no banco de dados
        correntista.setConta(conta);
        repository.save(correntista);
    }

    public Page<Corretista> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Corretista findByIdConta(Long id) throws CorrentistaException {
        return repository.findById(id).orElseThrow(() -> new CorrentistaException("NOT FOUND corretistas with id: " + id));
    }

    public Boolean ValideUser(String username,String password) {
         repository.findByUsername(username);
         return true;
    }

    @Transactional
    public void deleteById(Long id){
          repository.deleteById(id);
    }
}

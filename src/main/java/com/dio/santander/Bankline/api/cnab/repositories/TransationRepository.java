package com.dio.santander.Bankline.api.cnab.repositories;

import com.dio.santander.Bankline.api.cnab.entities.Transation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransationRepository extends JpaRepository<Transation, Long> {

    // select * from transation order by nome_loja asc, id desc  esta e a consulta
    public List<Transation> findAllByOrderByNomeLojaAscIdDesc();
}

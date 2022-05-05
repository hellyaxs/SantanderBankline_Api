package com.dio.santander.Bankline.api.repository;

import com.dio.santander.Bankline.api.Entities.Movimentacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MovimentacaoRepository extends JpaRepository<Movimentacao,Long> {


    List<Movimentacao> findByIdConta(Long IdConta);
}

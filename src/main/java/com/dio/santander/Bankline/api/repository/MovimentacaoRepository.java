package com.dio.santander.Bankline.api.repository;

import com.dio.santander.Bankline.api.Entities.Movimentacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovimentacaoRepository extends JpaRepository<Movimentacao,Long> {
}

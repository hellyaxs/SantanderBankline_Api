package com.dio.santander.Bankline.api.repository;

import com.dio.santander.Bankline.api.Entities.Corretista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CorretistaRepository extends JpaRepository<Corretista,Long> {
}

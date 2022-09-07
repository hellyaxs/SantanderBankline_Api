package com.dio.santander.Bankline.api.repository;

import com.dio.santander.Bankline.api.Entities.Corretista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CorretistaRepository extends JpaRepository<Corretista,Long> {

    @Query("SELECT e FROM Corretista e JOIN FETCH e.roles WHERE e.username = (:username)")
    public Optional<Corretista> findByUsername(@Param("username") String username);
}

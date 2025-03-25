package com.bank.infrastructure.repository;

import com.bank.infrastructure.entity.UserEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface UserEntityRepository extends ReactiveCrudRepository<UserEntity, UUID> {

    Mono<Boolean> existsByTaxNumber(String taxNumber);

    Mono<Boolean> existsByEmail(String email);
}

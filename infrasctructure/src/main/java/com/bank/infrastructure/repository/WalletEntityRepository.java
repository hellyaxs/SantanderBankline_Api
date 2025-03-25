package com.bank.infrastructure.repository;

import com.bank.infrastructure.entity.WalletEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface WalletEntityRepository extends ReactiveCrudRepository<WalletEntity, Long> {

    Mono<WalletEntity> findByUserEntityTaxNumber(String taxNumber);
}

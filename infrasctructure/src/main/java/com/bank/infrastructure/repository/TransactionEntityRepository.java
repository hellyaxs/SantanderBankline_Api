package com.bank.infrastructure.repository;

import com.bank.infrastructure.entity.TransactionEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;


public interface TransactionEntityRepository extends ReactiveCrudRepository<TransactionEntity, Long> {
}

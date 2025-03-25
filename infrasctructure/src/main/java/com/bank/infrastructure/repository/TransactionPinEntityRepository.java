package com.bank.infrastructure.repository;

import com.bank.infrastructure.entity.TransactionPinEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface TransactionPinEntityRepository extends ReactiveCrudRepository<TransactionPinEntity, Long> {
}

package com.bank.infrastructure.services;

import com.bank.application.gateway.CreateTransactionGateway;
import com.bank.core.domain.Transaction;
import com.bank.infrastructure.mapper.TransactionMapper;
import com.bank.infrastructure.repository.TransactionEntityRepository;
import org.springframework.stereotype.Service;

import java.util.Objects;

import static com.bank.infrastructure.utils.Utilities.log;

@Service
public class CreateTransactionGatewayImpl implements CreateTransactionGateway {
    private TransactionEntityRepository transactionEntityRepository;
    private TransactionMapper transactionMapper;

    public CreateTransactionGatewayImpl(TransactionEntityRepository transactionEntityRepository, TransactionMapper transactionMapper) {
        this.transactionEntityRepository = transactionEntityRepository;
        this.transactionMapper = transactionMapper;
    }

    @Override
    public Transaction create(Transaction transaction){
        try {
            log.info("Inicio da criação da transação::CreateTransactionGatewayImpl");
            var transactionEntity = transactionMapper.createTransaction(transaction);
            return transactionMapper.toTransaction(Objects.requireNonNull(transactionEntityRepository.save(transactionEntity).block()));
        }catch (Exception e){
            log.error("Houve erro ao criar a transação::CreateTransactionGatewayImpl");
            return null;
        }
    }
}

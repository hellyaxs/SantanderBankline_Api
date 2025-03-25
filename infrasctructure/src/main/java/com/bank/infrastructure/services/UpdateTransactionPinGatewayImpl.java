package com.bank.infrastructure.services;

import com.bank.application.gateway.UpdateTransactionPinGateway;
import com.bank.core.domain.TransactionPin;
import com.bank.infrastructure.mapper.TransactionPinMapper;
import com.bank.infrastructure.repository.TransactionPinEntityRepository;
import org.springframework.stereotype.Service;

import java.util.Objects;

import static com.bank.infrastructure.utils.Utilities.log;

@Service
public class UpdateTransactionPinGatewayImpl implements UpdateTransactionPinGateway {

    private TransactionPinEntityRepository transactionPinEntityRepository;
    private TransactionPinMapper transactionPinMapper;

    public UpdateTransactionPinGatewayImpl(TransactionPinEntityRepository transactionPinEntityRepository, TransactionPinMapper transactionPinMapper) {
        this.transactionPinEntityRepository = transactionPinEntityRepository;
        this.transactionPinMapper = transactionPinMapper;
    }

    @Override
    public TransactionPin update(TransactionPin transactionPin) {
        log.info("Inicia da atualização da senha de transação");
        return transactionPinMapper.toTransactionPin(Objects.requireNonNull(transactionPinEntityRepository.save(transactionPinMapper.toTransactionPinEntityUpdate(transactionPin)).block()));
    }
}

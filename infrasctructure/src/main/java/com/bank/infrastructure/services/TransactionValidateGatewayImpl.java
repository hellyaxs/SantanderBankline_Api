package com.bank.infrastructure.services;

import com.bank.application.gateway.TransactionValidateGateway;
import com.bank.core.domain.Transaction;
//import com.bank.infrastructure.client.apivalidate.ApiValidateService;
import org.springframework.stereotype.Service;

import static com.bank.infrastructure.utils.Utilities.log;

@Service
public class TransactionValidateGatewayImpl implements TransactionValidateGateway {

    @Override
    public Boolean validate(Transaction transaction) {
        log.info("Inicio da validação da transação por serviço externo::TransactionValidateGatewayImpl");
        var response = true;
        if (response){
            log.info("Transação rejeitada::TransactionValidateGatewayImpl");
            return false;
        }
        log.info("Transação aprovada::TransactionValidateGatewayImpl");
        return true;
    }
}

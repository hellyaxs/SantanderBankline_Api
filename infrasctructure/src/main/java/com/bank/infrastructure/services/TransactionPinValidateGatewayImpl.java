package com.bank.infrastructure.services;

import com.bank.application.gateway.TransactionPinValidateGateway;
import com.bank.core.domain.TransactionPin;
import org.springframework.stereotype.Service;

import java.util.Objects;

import static com.bank.infrastructure.utils.Utilities.log;

@Service
public class TransactionPinValidateGatewayImpl implements TransactionPinValidateGateway {

    @Override
    public boolean validate(TransactionPin transactionPin, String pin) {
        log.info("Inicio da validação da senha de transação::TransactionPinValidateGatewayImpl");
        if (!Objects.equals(transactionPin.getPin(), pin)){
            log.info("Senha incorreta::TransactionPinValidateGatewayImpl");
            return false;
        }
        log.info("Senha correta::TransactionPinValidateGatewayImpl");
        return true;
    }
}

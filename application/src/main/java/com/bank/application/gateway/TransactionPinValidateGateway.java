package com.bank.application.gateway;

import com.bank.core.domain.TransactionPin;

public interface TransactionPinValidateGateway {
    boolean validate(TransactionPin transactionPin, String pin);
}

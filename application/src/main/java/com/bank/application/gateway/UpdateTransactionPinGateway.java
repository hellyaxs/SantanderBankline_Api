package com.bank.application.gateway;

import com.bank.core.domain.TransactionPin;

public interface UpdateTransactionPinGateway {

    TransactionPin update(TransactionPin transactionPin);
}

package com.bank.application.gateway;

import com.bank.core.domain.Transaction;

public interface TransactionValidateGateway {
    Boolean validate(Transaction transaction);
}

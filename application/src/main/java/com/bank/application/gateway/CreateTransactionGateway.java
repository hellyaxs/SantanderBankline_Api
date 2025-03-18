package com.bank.application.gateway;

import com.bank.core.domain.Transaction;

public interface CreateTransactionGateway {
    Transaction create(Transaction transaction) throws Exception;
}

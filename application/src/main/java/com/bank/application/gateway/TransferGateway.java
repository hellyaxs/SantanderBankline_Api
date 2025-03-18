package com.bank.application.gateway;

import com.bank.core.domain.Transaction;

public interface TransferGateway {
    Boolean transfer(Transaction transaction);
}

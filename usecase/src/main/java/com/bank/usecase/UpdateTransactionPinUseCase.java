package com.bank.usecase;

import com.bank.core.domain.TransactionPin;

public interface UpdateTransactionPinUseCase {
    TransactionPin update(TransactionPin transactionPin);
}

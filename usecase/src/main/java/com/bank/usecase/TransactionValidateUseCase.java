package com.bank.usecase;

import com.bank.core.domain.Transaction;
import com.bank.core.exception.TransferException;

public interface TransactionValidateUseCase {
    Boolean validate(Transaction transaction) throws TransferException;

}

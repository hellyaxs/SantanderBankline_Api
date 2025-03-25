package com.bank.usecase;

import com.bank.core.domain.Transaction;
import com.bank.core.exception.*;

public interface TransferUseCase {
    Boolean transfer(Transaction transaction) throws Exception;
}

package com.bank.usecase;

import com.bank.core.domain.TransactionPin;
import com.bank.core.exception.BadRequestException;
import com.bank.core.exception.PinException;
import com.bank.core.exception.TransferException;

public interface TransactionPinValidateUseCase {
    Boolean validate(TransactionPin transactionPin, String pin) throws TransferException, PinException, BadRequestException;
}

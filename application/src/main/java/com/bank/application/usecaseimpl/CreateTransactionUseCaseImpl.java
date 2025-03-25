package com.bank.application.usecaseimpl;

import com.bank.application.gateway.CreateTransactionGateway;
import com.bank.core.domain.Transaction;
import com.bank.core.domain.Wallet;
import com.bank.core.exception.TransferException;
import com.bank.core.exception.enums.ErrorCodeEnum;
import com.bank.usecase.CreateTransactionUseCase;

import java.math.BigDecimal;

public class CreateTransactionUseCaseImpl implements CreateTransactionUseCase {
    final private CreateTransactionGateway createTransactionGateway;

    public CreateTransactionUseCaseImpl(CreateTransactionGateway createTransactionGateway) {
        this.createTransactionGateway = createTransactionGateway;
    }

    @Override
    public Transaction create(Wallet to, Wallet from, BigDecimal value) throws Exception {
        var transaction = new Transaction(to, from, value);
        var transactionSaved = createTransactionGateway.create(transaction);

        if (transactionSaved == null){
            throw new TransferException(ErrorCodeEnum.TR0003.getMessage(), ErrorCodeEnum.TR0003.getCode());
        }

        return  transactionSaved;
    }
}

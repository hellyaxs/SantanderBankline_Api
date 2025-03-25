package com.bank.application.usecaseimpl;

import com.bank.application.gateway.TransferGateway;
import com.bank.core.domain.Transaction;
import com.bank.core.exception.*;
import com.bank.core.exception.enums.ErrorCodeEnum;
import com.bank.usecase.*;

public class TransferUseCaseImpl implements TransferUseCase {

    final private TransferGateway transferGateway;
    public TransferUseCaseImpl(TransferGateway transferGateway) {
        this.transferGateway = transferGateway;
    }


    @Override
    public Boolean transfer(Transaction transaction) throws Exception {
        transaction.getFromWallet().transfer(transaction.getValue());
        transaction.getToWallet().receiveTransfer(transaction.getValue());
        if (!transferGateway.transfer(transaction)){
            throw new InternalServerErrorException(ErrorCodeEnum.TR0003.getMessage(), ErrorCodeEnum.TR0003.getCode());
        }
        return true;
    }
}

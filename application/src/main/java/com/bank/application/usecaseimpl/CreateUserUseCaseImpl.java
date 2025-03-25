package com.bank.application.usecaseimpl;

import com.bank.application.gateway.CreateUserGateway;
import com.bank.core.domain.TransactionPin;
import com.bank.core.domain.User;
import com.bank.core.domain.Wallet;
import com.bank.core.exception.EmailException;
import com.bank.core.exception.InternalServerErrorException;
import com.bank.core.exception.TaxNumberException;
import com.bank.core.exception.TransactionPinException;
import com.bank.core.exception.enums.ErrorCodeEnum;
import com.bank.usecase.*;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

public class CreateUserUseCaseImpl implements CreateUserUseCase {

    private CreateUserGateway createUserGateway;

    public CreateUserUseCaseImpl(CreateUserGateway createUserGateway) {

        this.createUserGateway = createUserGateway;

    }

    @Override
    public Mono<Void> create(User user, String pin) throws TaxNumberException, EmailException, TransactionPinException, InternalServerErrorException {

        createUserGateway.create(user, new Wallet(new TransactionPin(pin), BigDecimal.ZERO, user)).onErrorResume(throwable -> {
            if (throwable instanceof TaxNumberException) {
                return Mono.error(new TaxNumberException(ErrorCodeEnum.ON0001.getMessage(), ErrorCodeEnum.ON0001.getCode()));
            } else if (throwable instanceof EmailException) {
                return Mono.error(new EmailException(ErrorCodeEnum.ON0002.getMessage(), ErrorCodeEnum.ON0002.getCode()));

            } else {
                return Mono.error(new InternalServerErrorException(ErrorCodeEnum.ON0004.getMessage(), ErrorCodeEnum.ON0004.getCode()));
            }
        });
       return Mono.empty();

    }

}

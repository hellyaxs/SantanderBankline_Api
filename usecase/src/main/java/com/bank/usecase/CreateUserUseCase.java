package com.bank.usecase;

import com.bank.core.domain.User;
import com.bank.core.exception.EmailException;
import com.bank.core.exception.InternalServerErrorException;
import com.bank.core.exception.TaxNumberException;
import com.bank.core.exception.TransactionPinException;
import reactor.core.publisher.Mono;

public interface CreateUserUseCase {
    Mono<Void> create(User user, String pin) throws TaxNumberException, EmailException, TransactionPinException, InternalServerErrorException;
}

package com.bank.usecase;

import com.bank.core.exception.EmailException;
import reactor.core.publisher.Mono;

public interface EmailAvailableUseCase {

    Mono<Boolean> emailAvailableEmail(String email) throws EmailException;
}

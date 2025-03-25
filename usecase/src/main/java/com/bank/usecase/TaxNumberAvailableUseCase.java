package com.bank.usecase;

import com.bank.core.exception.TaxNumberException;
import reactor.core.publisher.Mono;

public interface TaxNumberAvailableUseCase {
    Mono<Boolean> taxNumberAvaliable(String taxNumber) throws TaxNumberException;
}

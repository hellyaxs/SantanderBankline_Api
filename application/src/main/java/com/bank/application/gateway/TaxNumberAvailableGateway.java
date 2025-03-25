package com.bank.application.gateway;

import reactor.core.publisher.Mono;

public interface TaxNumberAvailableGateway {
    Mono<Boolean> taxNumberAvailable(String taxNumber);
}

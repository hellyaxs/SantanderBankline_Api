package com.bank.application.gateway;

import reactor.core.publisher.Mono;

public interface EmailAvailableGateway {
    Mono<Boolean> emailAvailable(String email);
}

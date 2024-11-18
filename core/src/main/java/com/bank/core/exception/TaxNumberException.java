package com.bank.core.exception;

import reactor.core.publisher.Mono;

public class TaxNumberException extends Exception {

    private Mono<String> code;

    public TaxNumberException(Mono<String> message, Mono<String> code) {
        super();
        this.code = code;
        message.subscribe();
    }

    public Mono<String> getCode() {
        return code;
    }
}

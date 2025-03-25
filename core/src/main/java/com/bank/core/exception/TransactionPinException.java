package com.bank.core.exception;

import reactor.core.publisher.Mono;

public class TransactionPinException extends Throwable {
    private Mono<String> code;
    public TransactionPinException(Mono<String> message, Mono<String> code) {
        super();
        this.code = code;
    }

    public Mono<String> getCode() {
        return code;
    }
}

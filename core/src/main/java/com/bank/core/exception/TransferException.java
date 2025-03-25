package com.bank.core.exception;

import reactor.core.publisher.Mono;

public class TransferException extends Exception {
    private Mono<String> code;
    public TransferException(Mono<String> message, Mono<String> code) {
        super();
        this.code = code;
    }

    public Mono<String> getCode() {
        return code;
    }
}

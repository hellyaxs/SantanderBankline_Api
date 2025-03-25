package com.bank.core.exception;

import reactor.core.publisher.Mono;

public class NotFoundException extends Exception{

    private Mono<String> code;

    public NotFoundException(Mono<String> message, Mono<String> code) {
        super();
        this.code = code;
    }

    public Mono<String> getCode() {
        return code;
    }
}

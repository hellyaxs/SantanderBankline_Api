package com.bank.core.exception;

import reactor.core.publisher.Mono;

public class InternalServerErrorException extends Exception{

    private Mono<String> code;

    public InternalServerErrorException(Mono<String> message, Mono<String> code) {
        super();
        this.code = code;
    }

    public Mono<String> getCode() {
        return code;
    }
}

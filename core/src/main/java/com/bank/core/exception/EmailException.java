package com.bank.core.exception;

import reactor.core.publisher.Mono;

public class EmailException extends Exception{

    private Mono<String> code;

    public EmailException(Mono<String> message, Mono<String> code) {
        super();
        this.code = code;
    }

    public Mono<String> getCode() {
        return code;
    }
}

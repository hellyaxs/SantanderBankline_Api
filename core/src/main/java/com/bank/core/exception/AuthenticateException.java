package com.bank.core.exception;

import reactor.core.publisher.Mono;

public class AuthenticateException extends Exception{

    private Mono<String> code;

    public AuthenticateException(Mono<String> message, Mono<String> code) {
        super();
        this.code = code;
    }

    public Mono<String> getCode() {
        return code;
    }
}

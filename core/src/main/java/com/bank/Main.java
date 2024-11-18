package com.bank;

import reactor.core.publisher.Mono;

public class Main {
    public static void main(String[] args) {
        Mono.just("Hello, World!")
                .map(String::toUpperCase)
                .subscribe(System.out::println);
    }
}
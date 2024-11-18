package com.bank;

import reactor.core.publisher.Mono;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Mono.just("Hello, World!")
                .map(String::toUpperCase)
                .subscribe(System.out::println);
    }
}